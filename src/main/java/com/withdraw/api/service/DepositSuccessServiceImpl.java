package com.withdraw.api.service;

import com.withdraw.domain.entity.DepositSuccessEntity;
import com.withdraw.domain.repository.DepositSuccessRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DepositSuccessServiceImpl implements DepositSuccessService {
    @Autowired
    DepositSuccessRepository depositSuccessRepository;


    @Override
    public Optional<DepositSuccessEntity> findById(Long id) {
        return depositSuccessRepository.findById(id);
    }

    @Override
    public Optional<DepositSuccessEntity> findByTransId(Long transId) {
        return depositSuccessRepository.findByTransId(transId);
    }

    @Override
    public void save(DepositSuccessEntity depositSuccess) {
        depositSuccessRepository.save(depositSuccess);
    }

    @Override
    public Page<DepositSuccessEntity> getAll(Pageable pageable) {
        return null;
    }
}
