package com.testcloud.common.filter;

import com.testcloud.common.BaseContext;
import com.testcloud.common.exception.UncheckedFilterException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@DisplayName("用户上下文过滤器测试")
class UserContextFilterTest {

    private final UserContextFilter filter = new UserContextFilter();

    @Test
    @DisplayName("正常场景：Header 存在 ID 时应正确绑定作用域")
    void should_bind_user_id_when_header_exists() throws Exception {
        // 1. 准备 Mock 对象
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        when(request.getHeader("X-User-Id")).thenReturn("888");

        // 2. 执行并验证
        // 在 chain.doFilter 执行时，我们去 BaseContext 拿值，验证 ScopedValue 是否生效
        doAnswer(invocation -> {
            assertThat(BaseContext.getCurrentId()).isEqualTo(888L);
            return null;
        }).when(chain).doFilter(request, response);

        filter.doFilter(request, response, chain);

        // 3. 验证结束后，作用域应自动销毁（由于 getCurrentId 无作用域抛异常，这里不直接测，主要测逻辑执行了）
        verify(chain, times(1)).doFilter(request, response);
    }

    @Test
    @DisplayName("异常场景：业务逻辑抛错时应被包装为 UncheckedFilterException")
    void should_wrap_exception_when_logic_fails() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        when(request.getHeader("X-User-Id")).thenReturn("123");

        // 模拟后续业务抛出常规 Exception
        doThrow(new RuntimeException("DB Error")).when(chain).doFilter(request, response);

        assertThatThrownBy(() -> filter.doFilter(request, response, chain))
                .isExactlyInstanceOf(UncheckedFilterException.class)
                .hasCauseInstanceOf(RuntimeException.class);
    }
}