package com.myproject.nacos.config;

import com.alibaba.cloud.nacos.client.NacosPropertySource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lkxl
 */
@RefreshScope
@RestController
@RequestMapping("config")
public class DataBaseController {
    // 使用原生注解@Value()导入配置
    @Value("${mysql.url}")
    private String url;
    @Value("${mysql.name}")
    private String name;
    @Value("${mysql.password}")
    private String password;

    @GetMapping("database")
    public String providerTest()
    {
        return "success:(url:" + url + ",name:" + name + ",password:" + password +")";
    }
}
