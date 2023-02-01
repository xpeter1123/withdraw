package com.withdraw.api.model;

import lombok.Data;

@Data
public class DepositInitialRequest {
    private String userId;
    private Double amount;
}
