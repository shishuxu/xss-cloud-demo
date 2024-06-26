package com.xss.user;

import com.xss.parent.feign.EnableCommonFeignClients;
import com.xss.parent.redisson.EnableRedissonExecutor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@MapperScan("com.xss.user.mapper")
@SpringBootApplication
@EnableCommonFeignClients
@EnableRedissonExecutor
public class XssUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(XssUserApplication.class, args);
    }

}