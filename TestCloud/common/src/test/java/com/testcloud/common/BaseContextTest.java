package com.testcloud.common;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("基础上下文组件测试 - JDK 25 生产标准")
class BaseContextTest {

    @Test
    @DisplayName("正常场景：作用域内应能正确获取用户ID")
    void should_get_user_id_within_scope() {
        Long expectedId = 123L;

        BaseContext.executeWithUser(expectedId, () -> {
            assertThat(BaseContext.getCurrentId())
                    .describedAs("在绑定作用域内，获取到的ID必须与输入一致")
                    .isEqualTo(expectedId);
        });
    }

    @Test
    @DisplayName("异常场景：无作用域访问应抛出 IllegalStateException")
    void should_throw_exception_when_no_scope() {
        assertThatThrownBy(BaseContext::getCurrentId)
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no user_id in thread");
    }

    @Test
    @DisplayName("边界场景：嵌套作用域应遵循就近原则（影子效应）")
    void should_support_nested_scopes() {
        Long outerId = 1L;
        Long innerId = 2L;

        BaseContext.executeWithUser(outerId, () -> {
            assertThat(BaseContext.getCurrentId()).isEqualTo(outerId);

            // 嵌套绑定
            BaseContext.executeWithUser(innerId, () -> {
                assertThat(BaseContext.getCurrentId()).isEqualTo(innerId);
            });

            // 退出内层后应恢复外层
            assertThat(BaseContext.getCurrentId()).isEqualTo(outerId);
        });
    }
}
