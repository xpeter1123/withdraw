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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
public class WorkerController {


    @PostMapping(value = "/worker/{command}/{workerId}")
    public ResponseEntity<?> transactionInitial(@PathVariable String command, @PathVariable String workerId)  {

        return new ResponseEntity<Object>(new Object(), HttpStatus.OK);
    }
}
