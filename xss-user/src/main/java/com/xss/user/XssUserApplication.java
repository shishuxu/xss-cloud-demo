package com.xss.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableDiscoveryClient
@MapperScan("com.xss.user.mapper")
@SpringBootApplication
@EnableFeignClients(basePackages = "com.xss.parent.feign")
public class XssUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(XssUserApplication.class, args);
    }

}
