//package com.xss.gateway.filter;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import io.netty.util.internal.StringUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.ObjectUtils;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.core.io.buffer.DataBufferUtils;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.io.UnsupportedEncodingException;
//import java.nio.charset.StandardCharsets;
//import java.util.Optional;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//@Slf4j
//@Component
//public class CommonFilter implements GlobalFilter, Ordered {
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest serverHttpRequest = exchange.getRequest();
//        String method = serverHttpRequest.getMethodValue();
//        System.out.println(method);
//        String contentType = serverHttpRequest.getHeaders().getFirst("Content-Type");
//        if (StringUtil.isNullOrEmpty(contentType)) {
//            contentType = "DEFAULT";
//        }
//        if (method.equals("POST") || contentType.startsWith("multipart/form-data")) {
//            return DataBufferUtils.join(exchange.getRequest().getBody())
//                    .flatMap(dataBuffer -> {
//                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
//                        dataBuffer.read(bytes);
//                        try {
//                            String bodyString = new String(bytes, "utf-8");
//                            System.out.println(formatStr(bodyString));
//                            JSONObject json = JSON.parseObject(bodyString);
//                            String token = json.getString("token");
//                            if (authCheck(token)) {
//                                exchange.getAttributes().put("POST_BODY", bodyString);
//                            } else {
//                                ServerHttpResponse response = exchange.getResponse();
//                                JSONObject responseBody = new JSONObject();
//                                responseBody.put("msg", "鉴权失败");
//                                byte[] bits = responseBody.toJSONString().getBytes(StandardCharsets.UTF_8);
//                                DataBuffer buffer2 = response.bufferFactory().wrap(bits);
//                                response.setStatusCode(HttpStatus.UNAUTHORIZED);
//                                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
//                                return response.writeWith(Mono.just(buffer2));
//                            }
//
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//                        DataBufferUtils.release(dataBuffer);
//                        Flux<DataBuffer> cachedFlux = Flux.defer(() -> {
//                            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
//                            return Mono.just(buffer);
//                        });
//
//                        ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(
//                                exchange.getRequest()) {
//                            @Override
//                            public Flux<DataBuffer> getBody() {
//                                return cachedFlux;
//                            }
//                        };
//                        return chain.filter(exchange.mutate().request(mutatedRequest).build());
//                    });
//
//        } else if (method.equals("GET")) {
//            String token = serverHttpRequest.getQueryParams().getFirst("token");
//            System.out.println(token);
//            if (authCheck(token)) {
//                return chain.filter(exchange);
//            } else {
//                ServerHttpResponse response = exchange.getResponse();
//                JSONObject responseBody = new JSONObject();
//                responseBody.put("msg", "鉴权失败");
//                byte[] bits = responseBody.toJSONString().getBytes(StandardCharsets.UTF_8);
//                DataBuffer buffer2 = response.bufferFactory().wrap(bits);
//                response.setStatusCode(HttpStatus.UNAUTHORIZED);
//                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
//                return response.writeWith(Mono.just(buffer2));
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public int getOrder() {
//   标准优先级，值越小优先级越高
//        return 0;
//    }
//
//    private String formatStr(String str) {
//        if (str != null && str.length() > 0) {
//            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
//            Matcher m = p.matcher(str);
//            return m.replaceAll("");
//        }
//        return str;
//    }
//
//    public boolean authCheck(String token) {
//        return ObjectUtils.equals(token, "qw");
//    }
//}
//
//
