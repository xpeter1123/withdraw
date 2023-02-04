package com.withdraw.api.model;

import lombok.Data;

@Data
public class DepositInitialRequest {
    private Long userId;
    private Double amount;
}
