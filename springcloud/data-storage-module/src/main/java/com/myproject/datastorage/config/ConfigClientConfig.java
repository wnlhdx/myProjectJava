package com.myproject.datastorage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@Configuration
@RefreshScope
public class ConfigClientConfig {
    // Spring Cloud Config 动态刷新功能启用
}
