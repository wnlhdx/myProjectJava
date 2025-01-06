package com.myproject.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service-route", r -> r.path("/users/**")
                        .uri("lb://USER-SERVICE")) // 转发到用户服务
                .route("data-processing-route", r -> r.path("/data/**")
                        .uri("lb://DATA-PROCESSING-SERVICE")) // 转发到数据处理服务
                .build();
    }
}
