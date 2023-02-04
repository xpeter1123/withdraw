package com.withdraw.core.process;

import com.withdraw.config.ProcessSetting;
import com.withdraw.utils.Methods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@Slf4j
public class ProcessInvoker implements BeanPostProcessor {
    private ConcurrentMap<String, ProcessBase> mapProcess = new ConcurrentHashMap<>();

    @Autowired
    ProcessSetting processSetting;

    public void runAllProcess() {
        log.info("mapProcess={}", mapProcess);
        Arrays.stream(processSetting.getListProcessIds().split(",")).forEach(processId -> {
            Methods.invoke(processId, processSetting, ProcessSetting.Settings.class)
                    .ifPresent(setting -> {
                        if (setting.isRunning()) {
                            findProcessKey(processId).ifPresent(processKey -> {
                                mapProcess.get(processKey).startProcess();
                                log.info("started process: {}", processKey);
                            });
                        } else {
                            log.info("process {} setting off", setting);
                        }
                    });
        });
    }

    private Optional<String> findProcessKey(String key) {
        return mapProcess.keySet().stream().filter(string -> string.equals(key)).findFirst();
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Process.class)) {
            if (bean instanceof ProcessBase) {
                String key = bean.getClass().getAnnotation(Process.class).key();
                mapProcess.put(key, (ProcessBase) bean);
            }
        }
        return bean;
    }
}
