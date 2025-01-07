package com.myproject.user.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 关闭 CSRF 防护，如果不使用表单登录或其他交互式认证，可以禁用 CSRF
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/user/**").authenticated()  // 仅授权"/user/**"路径
                        .anyRequest().permitAll()  // 允许其他路径无认证
                )
                .httpBasic(withDefaults())  // 使用默认 HTTP Basic 认证配置，适配新版本
                .formLogin(withDefaults());  // 使用表单登录，适配新版本（如果需要表单登录）

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
