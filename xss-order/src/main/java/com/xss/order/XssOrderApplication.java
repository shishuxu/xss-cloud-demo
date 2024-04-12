package com.xss.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(basePackages = "com.xss.order.mapper")
@SpringBootApplication
public class XssOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(XssOrderApplication.class, args);
    }

}
