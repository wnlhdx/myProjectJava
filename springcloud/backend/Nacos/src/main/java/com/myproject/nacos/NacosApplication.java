package com.myproject.nacos;

import com.alibaba.cloud.nacos.client.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.web.client.RestTemplate;

/**
 * @author lkxl
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        //spring官网建议RestTemplateBuilder构造器来进行构造，这里体现了构造者设计模式
        return builder.build();
    }
}