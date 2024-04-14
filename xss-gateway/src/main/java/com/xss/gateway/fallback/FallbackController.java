package com.xss.gateway.fallback;

import com.alibaba.fastjson2.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;

/**
 * 自定义熔断结果
 *
 * @author xss
 * @version 1.0
 * @date 2024/04/13
 */
@Slf4j
@Component
public class FallbackController implements HandlerFunction<ServerResponse> {
    @HystrixCommand
    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        Optional<Object> originalUris = serverRequest.attribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        originalUris.ifPresent(originalUri -> log.error("网关执行请求:{}失败,hystrix服务降级处理", originalUri));
        Map<String, Object> result = new HashMap<>();
        result.put("error", "服务熔断");
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(JSON.toJSONString(result)));
    }
}

