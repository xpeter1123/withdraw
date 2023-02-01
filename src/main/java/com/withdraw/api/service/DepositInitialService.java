package com.withdraw.api.service;

import com.withdraw.domain.entity.DepositInitialEntity;

import java.io.IOException;

public interface DepositInitialService {
     DepositInitialEntity createDepositInitial(Long transId, String userId, Double amount);
}
