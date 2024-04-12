package com.xss.parent.redisson;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;



import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xss
 * @version 1.0.0
 * @date 2022-11-22 17:00
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.redis")
public class RedissonConfig {

    /**
     * redis 地址
     */

    private String host;

    /**
     * 端口
     */

    private String port;

    /**
     * 密碼
     */

    private String password;


}

