package com.withdraw.api.service;

import com.withdraw.domain.entity.MerchantInfoEntity;

import java.util.Optional;


public interface MerchantInfoService {
    Iterable<MerchantInfoEntity> getAll();

    MerchantInfoEntity getByMerchantId(String merchantId);
}
