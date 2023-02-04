package com.withdraw.domain.repository;

import com.withdraw.domain.entity.DepositInitialEntity;
import com.withdraw.domain.entity.MerchantInfoEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantInfoRepository extends PagingAndSortingRepository<MerchantInfoEntity, Long>,
        JpaSpecificationExecutor<MerchantInfoEntity> {
    MerchantInfoEntity findByMerchantId(String merchantId);
}
