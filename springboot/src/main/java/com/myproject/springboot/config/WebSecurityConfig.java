package com.myproject.springboot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author lkxl
 */
@Configuration
public class WebSecurityConfig{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequests->
                        authorizeHttpRequests
                                .requestMatchers("/login-page").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                                .anyRequest().authenticated()
        );
        http.formLogin(formLogin->
                formLogin
                        .loginPage("login.html")
                        .loginProcessingUrl("/login-page")
                        .permitAll()
        );
        // 注意 6.2 版本里这里要使用 csrf.disable() 而不是 withDefault() 方法，网上很多使用 withDefault()方法的，个人实践不成功
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        //明文加密
        return NoOpPasswordEncoder.getInstance();
    }


}