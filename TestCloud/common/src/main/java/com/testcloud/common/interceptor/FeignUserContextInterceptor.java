package com.testcloud.common.interceptor;

import com.testcloud.common.BaseContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

/**
 * Feign 拦截器：实现微服务间的用户信息透传
 */
public class FeignUserContextInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        try {
            // 从当前线程的 ScopedValue 中提取 ID
            Long userId = BaseContext.getCurrentId();
            if (userId != null) {
                // 自动塞入 Header，实现链路追踪
                template.header("X-User-Id", userId.toString());
            }
        } catch (IllegalStateException e) {
            // 如果没有绑定用户信息（比如某些系统内部定时任务），则不处理
            // 这种“温和处理”能保证系统内部调用的兼容性
        }
    }
}