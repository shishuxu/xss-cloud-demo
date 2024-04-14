package com.xss.gateway.fallback;

import com.alibaba.fastjson2.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        Optional<Object> originalUris = serverRequest.attribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        originalUris.ifPresent(originalUri -> log.error("网关执行请求:{}失败,hystrix服务降级处理", originalUri));
        Map<String, Object> result = new HashMap<>();
        result.put("error", "服务熔断");
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(JSON.toJSONString(result)));
    }
//    //线程池隔离()
//    @HystrixCommand(fallbackMethod = "降级方法名xxx",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.strategy",value = "THREAD"),  //隔离策略:THREAD(默认,可不设置)
//            @HystrixProperty(name = "execution.isolation.thread.timeoutinMilliseconds", value = "10"),  //配置命令执行的超时时间
//            @HystrixProperty(name = "execution.timeout.enabled", value = "true"),  //是否启用超时时间
//            @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true") //执行超时的时候是否中断
//    })
//    public String xxx(@PathVariable("id") Integer id){
//        // 方法体
//    }
//
//
//    //信号量隔离
//    @HystrixCommand(fallbackMethod = "降级方法名xxx",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.strategy",value = "SEMAPHORE"),  //开启信号量隔离策略
//            @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "10")//设置信号量隔离允许的最大并发数，默认为10
//    })
//    public String xxx(@PathVariable("id") Integer id){
//        // 方法体
//    }
}

