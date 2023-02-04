package com.withdraw.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "merchant-info.settings")
@RefreshScope
@Data
@Configuration
public class MerchantRestTemplateSetting {
    private String requestTimeout;
    private String readTimeout;
}
