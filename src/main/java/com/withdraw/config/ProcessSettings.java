package com.withdraw.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "process.settings")
@RefreshScope
@Data
public class ProcessSettings {
    private Settings GMDR = new Settings();

    @Data public static class Settings {
        private boolean running = false;
    }
}
