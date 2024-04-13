package com.xss.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan(basePackages = "com.xss.order.mapper")
@SpringBootApplication
@EnableFeignClients(basePackages = "com.xss.parent.feign")
@EnableDiscoveryClient
public class XssOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(XssOrderApplication.class, args);
    }

}
