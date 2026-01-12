package com.testcloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 模拟解析 Token 的逻辑
        // 实际开发中这里会调 jwtUtils.parseToken(token)
        String userId = "888";

        // 2. 将解析出来的 userId 塞进 Header，传给下游微服务
        // 这样下游的 UserContextFilter 就能通过 getHeader("X-User-Id") 拿到值
        ServerHttpRequest request = exchange.getRequest().mutate()
                .header("X-User-Id", userId)
                .build();

        return chain.filter(exchange.mutate().request(request).build());
    }

    @Override
    public int getOrder() {
        return -1; // 优先级最高
    }
}