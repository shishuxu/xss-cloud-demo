package com.xss.gateway.fallback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * 自定义熔断配置
 *
 * @author xss
 * @version 1.0
 * @date 2024/04/13
 */
@Configuration
public class RouterFunctionConfiguration {
    @Autowired
    private FallbackController fallbackController;
    @SuppressWarnings("rawtypes")
    @Bean
    public RouterFunction routerFunction() {
        // 添加熔断url
        return RouterFunctions
                .route(RequestPredicates.path("/fallback").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        fallbackController);
    }
}
