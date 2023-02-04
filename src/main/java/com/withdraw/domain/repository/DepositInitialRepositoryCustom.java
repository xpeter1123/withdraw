package com.withdraw.domain.repository;

import com.withdraw.domain.entity.DepositInitialEntity;

import javax.persistence.LockModeType;

public interface DepositInitialRepositoryCustom {
    DepositInitialEntity findByIdAndLock(Long id, LockModeType lockMode);

}
