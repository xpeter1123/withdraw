package com.withdraw.api.model;

import com.withdraw.domain.entity.DepositInitialEntity;
import lombok.Data;

@Data
public class DepositInitialResponse {
    private String userId;
    private Double amount;

    private String transId;

    public DepositInitialResponse(String userId, Double amount, String transId) {
        this.userId = userId;
        this.amount = amount;
        this.transId = transId;
    }

    public DepositInitialResponse(DepositInitialEntity depositInitialEntity) {
        this.userId = depositInitialEntity.getUserId();
        this.amount = depositInitialEntity.getAmount();
        this.transId = transId;
    }
}
