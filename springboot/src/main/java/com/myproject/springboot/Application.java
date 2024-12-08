package com.myproject.springboot;
 


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author 31446
 */

@SpringBootApplication
@EnableJpaRepositories("com.myproject.springboot.mapper")
public class Application {
    public static void main(String[] args) {
            SpringApplication.run(Application.class,args);
    }
}