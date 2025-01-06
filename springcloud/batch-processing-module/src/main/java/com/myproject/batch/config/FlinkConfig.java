package com.myproject.batch.config;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlinkConfig {

    @Bean
    public StreamExecutionEnvironment flinkExecutionEnvironment() {
        return StreamExecutionEnvironment.getExecutionEnvironment();
    }
}
