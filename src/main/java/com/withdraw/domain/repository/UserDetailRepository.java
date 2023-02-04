package com.withdraw.domain.repository;

import com.withdraw.domain.entity.DepositInitialEntity;
import com.withdraw.domain.entity.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailRepository extends PagingAndSortingRepository<UserDetailEntity, Long>, JpaSpecificationExecutor<UserDetailEntity>{
    @Query(value = "SELECT * FROM user_detail where user_id = ?1 limit 1 for update", nativeQuery = true)
    Optional<UserDetailEntity> findOneAndLock(Long userId);
}
