package com.xss.parent.redisson;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 分佈式鎖註解
 * @author xss
 * @version 1.0.0
 * @date 2022-11-22 17:00
 */


//保留時間
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
//作用類型
@Target({java.lang.annotation.ElementType.TYPE})
@Documented
@Import(RedissonInit.class)
@Configuration
public @interface EnableRedissonExecutor {
}

