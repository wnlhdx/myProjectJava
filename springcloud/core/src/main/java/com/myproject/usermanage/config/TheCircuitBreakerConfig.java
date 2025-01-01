package com.myproject.usermanage.config;


import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class TheCircuitBreakerConfig {

    @Bean
    public CircuitBreaker defaultCircuitBreaker() {
        // 配置熔断器的规则
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)  // 失败率达到 50% 时触发熔断
                .waitDurationInOpenState(Duration.ofMillis(1000))  // 熔断状态持续时间为1秒
                .slidingWindowSize(10)  // 滑动窗口大小
                .build();

        return CircuitBreaker.of("default", config);
    }



}