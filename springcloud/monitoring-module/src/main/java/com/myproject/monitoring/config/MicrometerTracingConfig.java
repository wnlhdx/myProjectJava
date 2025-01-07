package com.myproject.monitoring.config;

import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.SpanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicrometerTracingConfig {

    @Bean
    public SpanCustomizer spanCustomizer(Tracer tracer) {
        // 创建一个新的 Span，和当前活动 Span 关联
        return tracer.nextSpan().name("custom-span");
    }
}
