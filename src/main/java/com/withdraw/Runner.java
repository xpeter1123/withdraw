package com.withdraw;

import com.withdraw.api.service.DepositInitialService;
import com.withdraw.config.ProcessSetting;
import com.withdraw.core.process.ProcessInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements ApplicationRunner {

    @Autowired
    ProcessInvoker processInvoker;

    @Autowired
    ProcessSetting processSetting;

    @Autowired
    DepositInitialService depositInitialService;

    @Override
    public void run(ApplicationArguments args) {
        //  processInvoker.runAllProcess();
        depositInitialService.createDepositInitial(1l, 1l, 1.0);
    }
}
