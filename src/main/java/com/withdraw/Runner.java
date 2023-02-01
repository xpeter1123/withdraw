package com.withdraw;

import com.withdraw.api.service.DepositInitialService;
import com.withdraw.config.ProcessSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements ApplicationRunner {
    @Autowired
    DepositInitialService depositInitialService;

    @Override
    public void run(ApplicationArguments args) {
      //  depositInitialService.createDepositInitial(1l,"1", 1.0);
        ProcessSettings processSettings = new ProcessSettings();
        ProcessSettings.Settings processSetting = processSettings.getGMDR();
        System.out.println(processSetting);
    }
}
