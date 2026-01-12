package com.testcloud.common.config;

import com.testcloud.common.filter.UserContextFilter;
import com.testcloud.common.interceptor.FeignUserContextInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class CommonAutoConfiguration {

    @Bean
    public FeignUserContextInterceptor feignUserContextInterceptor() {
        return new FeignUserContextInterceptor();
    }

    @Bean
    public FilterRegistrationBean<UserContextFilter> userContextFilter() {
        FilterRegistrationBean<UserContextFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UserContextFilter());
        registrationBean.addUrlPatterns("/*");
        // 关键点：设置为最高优先级，确保它在业务代码执行前最早绑定上下文
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }
}