package com.myproject.springboot;
 


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * @author 31446
 */

@SpringBootApplication
@EnableR2dbcRepositories("com.myproject.springboot.mapper")
//@EnableRabbit
public class Application {
    public static void main(String[] args) {
            SpringApplication.run(Application.class,args);
    }
}