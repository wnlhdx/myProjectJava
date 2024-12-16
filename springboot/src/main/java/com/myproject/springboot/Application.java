package com.myproject.springboot;
 


import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author 31446
 */

@SpringBootApplication
@EnableJpaRepositories("com.myproject.springboot.mapper")
@EnableRabbit
public class Application {
    public static void main(String[] args) {
            SpringApplication.run(Application.class,args);
    }
}