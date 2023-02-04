package com.withdraw.domain.repository;

import com.withdraw.domain.entity.DepositInitialEntity;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;


public class DepositInitialRepositoryCustomImpl implements DepositInitialRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public DepositInitialEntity findByIdAndLock(Long id, LockModeType lockMode) {
      return null;
    }
}
