package com.withdraw.api.service;

import com.withdraw.domain.entity.DepositInitialEntity;
import com.withdraw.domain.repository.DepositRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositInitialServiceImpl implements DepositInitialService {
    private DepositRequestRepository depositRequestRepository;

    @Autowired
    public DepositInitialServiceImpl(DepositRequestRepository depositRequestRepository) {
        this.depositRequestRepository = depositRequestRepository;
    }

    @Override
    public DepositInitialEntity createDepositInitial(Long transId, String userId, Double amount) {
        DepositInitialEntity depositInitialEntity = new DepositInitialEntity(transId, userId, amount);
        return depositRequestRepository.save(depositInitialEntity);
    }
}
