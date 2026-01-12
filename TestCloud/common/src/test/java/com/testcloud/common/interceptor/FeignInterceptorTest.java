package com.testcloud.common.interceptor;

import com.testcloud.common.BaseContext;
import feign.RequestTemplate;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayName("Feign 拦截器链路透传测试")
class FeignInterceptorTest {

    private final FeignUserContextInterceptor interceptor = new FeignUserContextInterceptor();

    @Test
    @DisplayName("正常场景：Feign 调用时应自动从 ScopedValue 提取 ID 塞入 Header")
    void should_propagate_user_id_to_feign_header() {
        Long userId = 999L;
        RequestTemplate template = new RequestTemplate();

        // 在 ScopedValue 作用域内模拟 Feign 调用
        BaseContext.executeWithUser(userId, () -> {
            interceptor.apply(template);
        });

        // 验证 Header 是否被正确填充
        assertThat(template.headers().get("X-User-Id"))
                .containsExactly(userId.toString());
    }

    @Test
    @DisplayName("容错场景：无上下文时 Feign 调用不应报错")
    void should_not_throw_when_no_context() {
        RequestTemplate template = new RequestTemplate();

        // 模拟非请求链路（如定时任务）调用 Feign
        assertThatCode(() -> interceptor.apply(template))
                .doesNotThrowAnyException();

        assertThat(template.headers().get("X-User-Id")).isNull();
    }
}
