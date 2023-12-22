package com.myproject.springboot;
 

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author 31446
 */
@MapperScan("com.myproject.springboot.mapper")
@SpringBootApplication
//@EnableOpenApi
//@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class Application {
    public static void main(String[] args) {
            SpringApplication.run(Application.class,args);
    }
}