package com.withdraw.domain.repository;

import com.withdraw.domain.entity.DepositInitialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRequestRepository extends JpaRepository<DepositInitialEntity, Long> {
}
