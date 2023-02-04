package com.withdraw.api.service;

import com.withdraw.domain.entity.MerchantInfoEntity;
import com.withdraw.domain.repository.MerchantInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MerchantInfoServiceImpl implements MerchantInfoService {
    @Autowired
    MerchantInfoRepository merchantInfoRepository;

    @Override
    public Iterable<MerchantInfoEntity> getAll() {
        return merchantInfoRepository.findAll();
    }

    @Override
    public MerchantInfoEntity getByMerchantId(String merchantId) {
        return merchantInfoRepository.findByMerchantId(merchantId);
    }
}
