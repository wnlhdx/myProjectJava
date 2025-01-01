package com.myproject.usermanage.service;


import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class UserService {

    // 设置熔断器名字为 "userService" 的熔断器
    @CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
    public String getUserDetails(String userId) {
        // 模拟失败的服务调用
        if (Math.random() > 0.5) {
            throw new RuntimeException("Service is temporarily unavailable");
        }
        return "User details for " + userId;
    }

    // 熔断器触发后调用的回退方法
    public String fallbackMethod(String userId, Throwable throwable) {
        return "Fallback response for user: " + userId;
    }
}

