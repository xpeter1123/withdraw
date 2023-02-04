package com.withdraw.api.service;

import com.withdraw.api.model.merchant.DepositMerchantStatus;
import com.withdraw.api.model.merchant.MerchantBaseResponse;
import com.withdraw.core.event.EventPoolImpl;
import com.withdraw.core.event.model.ErrorDepositEvent;
import com.withdraw.domain.entity.*;
import com.withdraw.domain.repository.DepositInitialRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class DepositInitialServiceImpl implements DepositInitialService {
    @Autowired
    private DepositInitialRepository depositInitialRepository;

    @Autowired
    MerchantInfoService merchantInfoService;

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    DepositSuccessService depositSuccessService;

    @Autowired
    DepositErrorService depositErrorService;

    @Autowired
    EventPoolImpl eventPool;
    @Autowired
    @Qualifier("merchantRestTemplate")
    RestTemplate restTemplate;

    @Override
    @Transactional
    public DepositInitialEntity createDepositInitial(Long transId, Long userId, Double amount) {
        DepositInitialEntity depositInitialEntity = new DepositInitialEntity(transId, userId, amount);
        return depositInitialRepository.save(depositInitialEntity);
    }


    @Override
    @Transactional
    public Optional<DepositInitialEntity> findOneAndLock(Long id) {
        //  DepositInitialEntity rs = depositInitialRepository.findByIdAndLock(9l, LockModeType.OPTIMISTIC);
        Optional<DepositInitialEntity> x = depositInitialRepository.findOneAndLock(id);
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void handleDeposit(Long id) throws Exception {
        DepositInitialEntity depositEntity = depositInitialRepository.findOneAndLock(id).orElseThrow(() -> new NullPointerException("deposit entity not found " + id));

        //check try
        byte retry = depositEntity.getRetryCount();
        if (retry > 5) {
            handleError(depositEntity);
            return;
        }

        long updated = ZonedDateTime.parse(depositEntity.getAudit().getLastUpdate()).toInstant().toEpochMilli();
        byte retryCount = depositEntity.getRetryCount();
        double tryNext = updated + Math.pow(retryCount, 3) * TimeUnit.MINUTES.toMillis(2);
        if (tryNext > System.currentTimeMillis()) {
            depositEntity.getAudit().setLastUpdateNow();
            depositInitialRepository.save(depositEntity);
            return;
        }

        MerchantInfoEntity merchantInfoEntity = merchantInfoService.getByMerchantId(depositEntity.getMerchantId());
        MerchantBaseResponse merchantResponse = callMerchantApi(new Object(), merchantInfoEntity.getApiUrl());

        DepositMerchantStatus depositStatus = getDepositStatus(merchantResponse);
        switch (depositStatus) {
            case MONEY_MATCH: {
                checkTransactionNotProcessed(depositEntity.getTransId());
                UserDetailEntity userDetail = userDetailService.findOneAndLock(depositEntity.getUserId()).orElseThrow(() -> new NullPointerException("User detail not found" + depositEntity.getUserId()));
                userDetail.increaseBalance(depositEntity.getAmount());
                depositInitialRepository.delete(depositEntity);
                depositSuccessService.save(depositEntity.toDepositSuccessEntity());
                break;
            }

            case MONEY_NOT_MATCH: {
                depositEntity.increaseRetryCountAndSetMerchantResponse(merchantResponse.getContent());
                depositEntity.getAudit().setLastUpdateNow();
                return;
            }

            case NOT_FOUND_TRANS: {
                depositEntity.increaseRetryCountAndSetMerchantResponse(merchantResponse.getContent());
                depositEntity.getAudit().setLastUpdateNow();
                return;
            }
        }
    }

    private void checkTransactionNotProcessed(Long transId) throws Exception {
        Optional<DepositErrorEntity> depositError = depositErrorService.findByTransId(transId);
        if (depositError.isPresent()) {
            throw new Exception("transaction already processed and exited in error_table: " + transId);
        }

        Optional<DepositSuccessEntity> depositSuccess = depositSuccessService.findByTransId(transId);
        if (depositSuccess.isPresent()) {
            throw new Exception("transaction already processed and exited in success_table: " + transId);
        }
    }

    private DepositMerchantStatus getDepositStatus(MerchantBaseResponse merchantBaseResponse) {
        return DepositMerchantStatus.MONEY_NOT_MATCH;
    }

    private <T extends MerchantBaseResponse> T callMerchantApi(Object form, String url) {

        return null;
    }

    private void handleError(DepositInitialEntity depositEntity) {
        depositErrorService.save(depositEntity.toDepositErrorEntity());
        ErrorDepositEvent errorDepositEvent = new ErrorDepositEvent(depositEntity);
        eventPool.postEvent(errorDepositEvent);
    }

    @Override
    public Page<DepositInitialEntity> getAll(Specification<DepositInitialEntity> spec, Pageable sort) {
        return depositInitialRepository.findAll(spec, sort);
    }

    @Override
    public Page<DepositInitialEntity> getAll(Pageable pageable) {
        return depositInitialRepository.findAll(pageable);
    }
}
