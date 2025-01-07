package com.myproject.discovery.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EurekaConfig {

    @Bean
    public HealthIndicator eurekaHealthIndicator() {
        return () -> {
            // 自定义健康检查逻辑
            boolean isHealthy = true; // 示例逻辑
            return Health.up().build();
        };
    }
}
