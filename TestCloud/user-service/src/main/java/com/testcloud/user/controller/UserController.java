package com.testcloud.user.controller;

import com.testcloud.common.BaseContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/me")
    public String getMyContextId() {
        // 这里的逻辑非常简单，但它是检验 ScopedValue 是否成功的唯一标准
        // 如果 Filter 没干活，这里会直接抛 IllegalStateException
        // 如果串位了，这里会返回错误的 ID
        Long currentId = BaseContext.getCurrentId();
        return "当前上下文中绑定的用户ID是: " + currentId;
    }
}