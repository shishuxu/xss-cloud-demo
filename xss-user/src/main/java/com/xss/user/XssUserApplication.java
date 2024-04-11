package com.xss.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.xss.user.mapper")
@SpringBootApplication
public class XssUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(XssUserApplication.class, args);
    }

}
