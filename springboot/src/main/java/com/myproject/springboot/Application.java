package com.myproject.springboot;
 
import com.myproject.springboot.utils.OsOpertaion;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

/**
 * @author 31446
 */
@MapperScan("com.myproject.springboot.mapper")
@SpringBootApplication
//@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class Application {
    public static void main(String[] args) {
        if(OsOpertaion.checkOS().contains("Android")){

        }else {

        }
            SpringApplication.run(Application.class,args);
    }
}