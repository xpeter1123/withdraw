package com.withdraw.domain.repository;

import com.withdraw.domain.entity.DepositErrorEntity;
import com.withdraw.domain.entity.DepositInitialEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepositErrorRepository extends PagingAndSortingRepository<DepositErrorEntity, Long>,
        JpaSpecificationExecutor<DepositErrorEntity>{
    @Query(value = "SELECT * FROM deposit_error where id = ?1 limit 1 for update", nativeQuery = true)
    Optional<DepositErrorEntity> findOneAndLock(Long id);

    Optional<DepositErrorEntity> findByTransId(Long transId);
}
