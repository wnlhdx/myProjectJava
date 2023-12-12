package com.myproject.springboot;
 

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 31446
 */
@MapperScan("com.myproject.springboot.mapper")
@SpringBootApplication
//@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class Application {
    public static void main(String[] args) {
            SpringApplication.run(Application.class,args);
    }
}