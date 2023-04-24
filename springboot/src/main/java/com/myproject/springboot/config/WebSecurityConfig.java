package com.myproject.springboot.config;

import com.myproject.springboot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author lkxl
 */
@Configuration
public class WebSecurityConfig {
    @Autowired
    LoginService loginService;

    //加密方式
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(loginService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(AuthenticationManager authenticationManager, HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin()    //自定义登录页面
                .loginPage("/login")    //登录页面设置
                .loginProcessingUrl("/user/login")   //点击登录后访问的路径
                .defaultSuccessUrl("/user").permitAll()   //登录成功后跳转路径,permitAll表示无条件进行访问
                .and()
                .httpBasic(withDefaults())
                .authenticationManager(authenticationManager)
                .authorizeHttpRequests(authorize -> {
                    try {
                        authorize
                                // 放行登录接口
                                .requestMatchers("/login").permitAll()
                                // 放行资源目录
                                .requestMatchers("/static/**", "/resources/**").permitAll()
                                // 其余的都需要权限校验
                                .anyRequest().authenticated()
                                // 防跨站请求伪造
                                .and().csrf(AbstractHttpConfigurer::disable);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return httpSecurity.build();
    }
}