package com.withdraw.api.service;

import com.withdraw.domain.entity.DepositInitialEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.LockModeType;
import java.io.IOException;
import java.util.Optional;

public interface DepositInitialService {
     DepositInitialEntity createDepositInitial(Long transId, Long userId, Double amount);
     Optional<DepositInitialEntity> findOneAndLock(Long id);

     void handleDeposit(Long id) throws Exception;

     Page<DepositInitialEntity> getAll(Specification<DepositInitialEntity> spec, Pageable pageable);
     Page<DepositInitialEntity> getAll(Pageable pageable);
}
