package com.withdraw.api.service;

import com.withdraw.api.model.merchant.DepositMerchantStatus;
import com.withdraw.api.model.merchant.MerchantBaseResponse;
import com.withdraw.domain.entity.DepositInitialEntity;
import com.withdraw.domain.entity.MerchantInfoEntity;
import com.withdraw.domain.entity.UserDetailEntity;
import com.withdraw.domain.repository.DepositInitialRepository;
import com.withdraw.domain.repository.UserDetailRepository;
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
public class UserDetailServiceImpl implements UserDetailService {
    @Autowired
    UserDetailRepository userDetailRepository;

    @Override
    public Optional<UserDetailEntity> findOneAndLock(Long userId) {
        return userDetailRepository.findOneAndLock(userId);
    }
}
