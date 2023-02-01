package com.withdraw.api.controller;

import com.withdraw.api.model.DepositInitialRequest;
import com.withdraw.api.model.DepositInitialResponse;
import com.withdraw.api.service.DepositInitialService;
import com.withdraw.domain.entity.DepositInitialEntity;
import com.withdraw.utils.LogUtils;
import com.withdraw.utils.TransactionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
public class TransactionController {

    private DepositInitialService depositInitialService;

    @Autowired
    public TransactionController(DepositInitialService depositInitialService) {
        this.depositInitialService = depositInitialService;
    }

    @PostMapping(value = "/withdraw/transaction-init")
    public ResponseEntity<DepositInitialResponse> transactionInitial(@RequestBody DepositInitialRequest depositInitialRequest) throws IOException {
        LogUtils.logRestApiStart(log);
        LogUtils.logRestApiRequestBody(log, depositInitialRequest);
        Long transId = TransactionUtils.createTransactionId();
        DepositInitialEntity depositInitialEntity = depositInitialService.createDepositInitial(transId, depositInitialRequest.getUserId(), depositInitialRequest.getAmount());
        DepositInitialResponse depositInitialResponse = new DepositInitialResponse(depositInitialEntity);
        LogUtils.logRestApiResponseBody(log, depositInitialResponse);
        return new ResponseEntity<DepositInitialResponse>(depositInitialResponse, HttpStatus.OK);
    }
}
