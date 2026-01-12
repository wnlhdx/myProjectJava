package com.testcloud.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient // 即便没用 Nacos，加上它有时能激活 Cloud 的上下文
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Value("${spring.cloud.gateway.routes[0].uri:未读取}")
    private String testUri;

    @Bean
    public CommandLineRunner debugRoutes(RouteDefinitionLocator locator) {
        return args -> {
            locator.getRouteDefinitions().collectList().subscribe(routes -> {
                System.out.println("🚩 [核心审计] 当前网关路由表数量: " + routes.size());
                System.out.println("testurl"+testUri);
                routes.forEach(System.out::println);
            });
        };
    }
}

