package com.withdraw.api.service;

import com.withdraw.domain.entity.DepositSuccessEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DepositSuccessService {
     Optional<DepositSuccessEntity> findById(Long id);

     Optional<DepositSuccessEntity> findByTransId(Long transId);

     void save(DepositSuccessEntity depositSuccess);

     Page<DepositSuccessEntity> getAll(Pageable pageable);
}
