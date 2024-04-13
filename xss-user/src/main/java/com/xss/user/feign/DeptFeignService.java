//package com.xss.user.feign;
//
//import com.xss.parent.constant.AppConstant;
//import com.xss.user.fallback.DeptFallbackFactory;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.GetMapping;
////, url = "${spring.cloud.openfeign.client.config.postClient.url}"
//@Component
//@FeignClient(name = AppConstant.ORDER, fallbackFactory = DeptFallbackFactory.class)
//public interface DeptFeignService {
//    @GetMapping(value = "api/dept/getList", produces = MediaType.APPLICATION_JSON_VALUE)
//    String getList();
//}
