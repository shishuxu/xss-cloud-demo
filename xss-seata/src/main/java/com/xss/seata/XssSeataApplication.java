package com.xss.seata;

import com.xss.parent.feign.EnableCommonFeignClients;
import com.xss.parent.redisson.EnableRedissonExecutor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@MapperScan(basePackages = "com.xss.seata.mapper")
@SpringBootApplication
// 自定义注解，加载公共类的feign包
@EnableCommonFeignClients
@EnableDiscoveryClient
@EnableHystrix
@EnableRedissonExecutor
public class XssSeataApplication {

    public static void main(String[] args) {
        SpringApplication.run(XssSeataApplication.class, args);
    }

}
