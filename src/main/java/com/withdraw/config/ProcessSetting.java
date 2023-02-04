package com.withdraw.config;

import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component("processSetting")
@ConfigurationProperties(prefix = "process.settings")
@RefreshScope
@Data
@Configuration
public class ProcessSetting {
    private String listProcessIds = StringUtils.EMPTY;

    private Settings GMDR = new Settings();

    /* min thread in pool */
    private int corePoolSize;
    /* max thread opening in pool */
    private int maximumPoolSize;

    private int maxPendingQueueSize;

    @Data public static class Settings {
        private boolean running = false;

        private Integer warningTime;

        private Integer cancelTime;
    }
}
