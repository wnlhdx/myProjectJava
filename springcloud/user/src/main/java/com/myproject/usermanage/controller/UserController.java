package com.myproject.usermanage.controller;

import org.springframework.web.bind.annotation.*;
import com.myproject.usermanage.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        // 在实际应用中，应该进行数据库操作
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        // 登录验证逻辑，生成 JWT
        return "Login successful";
    }
}
