package com.withdraw.api.service;

import com.withdraw.domain.entity.DepositErrorEntity;
import com.withdraw.domain.entity.DepositInitialEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface DepositErrorService {
     Optional<DepositErrorEntity> findById(Long id);

     Page<DepositErrorEntity> getAll(Pageable pageable);

     Optional<DepositErrorEntity> findByTransId(Long transId);

     void save(DepositErrorEntity depositErrorEntity);
}
