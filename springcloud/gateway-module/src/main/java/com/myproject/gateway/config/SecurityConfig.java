package com.myproject.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login", "/signup").permitAll() // 开放登录和注册接口
                .anyRequest().authenticated()  // 其他请求需要认证
            .and()
            .oauth2Login() // 使用 OAuth2 进行登录
            .and()
            .oauth2ResourceServer()
            .jwt(); // 配置 JWT 认证
    }
}
