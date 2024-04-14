package com.xss.gateway.config;


import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 限流规则配置类
 * 配置文件中  key-resolver: 的value是下面限流规则的方法名字
 * 如
 *       key-resolver: "#{@pathKeyResolver}"
 * @author xss
 * @version 1.0
 * @date 2024/04/13
 */
@Configuration
public class KeyResolverConfiguration {
    /**
     * 以下配置要求请求路径中必须携带 userId 参数
     * @return url限流规则
     */
//    @Bean
//    public KeyResolver pathKeyResolver(){
//        return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
//    }

    /**
     * 以下配置要求请求路径中必须携带 userId 参数
     * @return 限流规则
     */
//    @Bean
//    public KeyResolver parameterKeyResolver()
//    {
//        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
//    }
    /**
     * 以下配置要求请求路径中必须携带 userId 参数
     * @return ip限流规则
     */
    @Bean
    public KeyResolver ipKeyResolver()
    {
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getHostName());
    }


}
