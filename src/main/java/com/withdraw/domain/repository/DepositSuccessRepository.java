package com.withdraw.domain.repository;

import com.withdraw.domain.entity.DepositInitialEntity;
import com.withdraw.domain.entity.DepositSuccessEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepositSuccessRepository extends PagingAndSortingRepository<DepositSuccessEntity, Long>, JpaSpecificationExecutor<DepositSuccessEntity>{
    @Query(value = "SELECT * FROM deposit_success where id = ?1 limit 1 for update", nativeQuery = true)
    Optional<DepositSuccessEntity> findOneAndLock(Long id);

    Optional<DepositSuccessEntity> findByTransId(Long transId);
}
