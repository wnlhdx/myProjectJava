package com.myproject.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import reactor.core.publisher.Mono;

@Component
@Order(2)
public class LoggingFilter implements GlobalFilter {

    private final Tracer tracer;

    public LoggingFilter(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Span span = tracer.nextSpan().name("gateway-request");
        System.out.println("Incoming request: " + exchange.getRequest().getURI());
        return chain.filter(exchange).contextWrite(Mono.deferContextual(ctx -> Mono.just(span)));
    }
}
