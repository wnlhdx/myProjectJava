package com.testcloud.api.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 声明式客户端：定义 user-service 暴露给外部的接口契约
 * name: 对应注册中心里的服务名
 */
@FeignClient(name = "user-service", path = "/users")
public interface UserClient {

    /**
     * 根据ID获取用户详情
     * 面试考点：Feign底层会根据这个注解生成 HTTP 请求
     */
    @GetMapping("/{id}")
    String getUserName(@PathVariable("id") Long id);

    /**
     * 专门用来验证透传逻辑的接口
     */
    @GetMapping("/me")
    String getMyContextId();
}