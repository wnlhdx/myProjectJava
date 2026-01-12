package com.testcloud.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc // 自动配置 MockMvc，模拟真实的 HTTP 请求进入
@DisplayName("用户服务集成链路测试")
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("全链路验证：Header -> Filter -> ScopedValue -> Controller")
    void should_return_user_id_from_scoped_value() throws Exception {
        // 模拟外部请求，带上 Header
        mockMvc.perform(get("/users/me")
                        .header("X-User-Id", "10086"))
                .andExpect(status().isOk())
                .andExpect(content().string("当前上下文中绑定的用户ID是: 10086"));
    }

    @Test
    @DisplayName("健壮性验证：无 Header 时 Controller 应如何表现（取决于你的 Filter 逻辑）")
    void should_handle_no_header_gracefully() throws Exception {
        // 如果你的 Filter 没拦截，BaseContext.getCurrentId() 可能会报错，这里能测出你的健壮性
        mockMvc.perform(get("/users/me"))
                .andExpect(status().isInternalServerError());
    }
}