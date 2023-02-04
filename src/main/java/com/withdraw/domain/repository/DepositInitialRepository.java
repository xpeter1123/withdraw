package com.withdraw.domain.repository;

import com.withdraw.domain.entity.DepositInitialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepositInitialRepository extends PagingAndSortingRepository<DepositInitialEntity, Long>,
        JpaSpecificationExecutor<DepositInitialEntity>,
        DepositInitialRepositoryCustom {
    @Query(value = "SELECT * FROM deposit_initial where id = ?1 limit 1 for update", nativeQuery = true)
    Optional<DepositInitialEntity> findOneAndLock(Long id);
}
