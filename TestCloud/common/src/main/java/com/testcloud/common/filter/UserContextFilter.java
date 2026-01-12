package com.testcloud.common.filter;

import com.testcloud.common.BaseContext;
import com.testcloud.common.exception.UncheckedFilterException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserContextFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String userIdStr = httpRequest.getHeader("X-User-Id");

        // 1. 实用主义检查：防范非法字符导致的 Crash
        Long userId = parseUserId(userIdStr);

        if (userId != null) {
            // 2. 核心逻辑：利用 ScopedValue 的栈绑定特性
            BaseContext.executeWithUser(userId, () -> {
                try {
                    chain.doFilter(request, response);
                } catch (Throwable e) {
                    // 3. 拒绝中间损耗：原样抛出，让 Spring 处理
                    throw new UncheckedFilterException(e);
                }
            });
        } else {
            chain.doFilter(request, response);
        }
    }

    private Long parseUserId(String str) {
        try {
            return (str != null && str.matches("\\d+")) ? Long.valueOf(str) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
