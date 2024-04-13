//package com.xss.user.fallback;
//
//import com.xss.user.feign.DeptFeignService;
//import feign.hystrix.FallbackFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class DeptFallbackFactory implements FallbackFactory<DeptFeignService> {
//    @Override
//    public DeptFeignService create(Throwable cause) {
//        return () -> "null";
//    }
//}
