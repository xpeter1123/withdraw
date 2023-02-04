package com.withdraw.api.service;

import com.withdraw.domain.entity.DepositErrorEntity;
import com.withdraw.domain.entity.DepositInitialEntity;
import com.withdraw.domain.repository.DepositErrorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DepositErrorServiceImpl implements DepositErrorService {
    @Autowired
    DepositErrorRepository depositErrorRepository;

    @Override
    public Optional<DepositErrorEntity> findById(Long id) {
        return depositErrorRepository.findById(id);
    }

    @Override
    public Page<DepositErrorEntity> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<DepositErrorEntity> findByTransId(Long transId) {
        return depositErrorRepository.findByTransId(transId);
    }

    @Override
    public void save(DepositErrorEntity depositErrorEntity) {
        depositErrorRepository.save(depositErrorEntity);
    }
}
