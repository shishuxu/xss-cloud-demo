package com.xss.order;

import com.xss.parent.feign.EnableCommonFeignClients;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan(basePackages = "com.xss.order.mapper")
@SpringBootApplication
@EnableCommonFeignClients
@EnableDiscoveryClient
@EnableHystrix
public class XssOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(XssOrderApplication.class, args);
    }

}
