package com.xss.seataorder;

import com.xss.parent.feign.EnableCommonFeignClients;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@MapperScan(basePackages = "com.xss.seataorder.mapper")
@SpringBootApplication
// 自定义注解，加载公共类的feign包
@EnableCommonFeignClients
@EnableDiscoveryClient
@EnableHystrix
public class XssSeataOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(XssSeataOrderApplication.class, args);
    }

}
