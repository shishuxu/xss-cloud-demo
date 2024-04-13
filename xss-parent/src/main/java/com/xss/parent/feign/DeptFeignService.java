package com.xss.parent.feign;

import com.xss.parent.constant.AppConstant;
import com.xss.parent.fallback.DeptFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
@Component
@FeignClient(name = AppConstant.ORDER, fallbackFactory = DeptFallbackFactory.class)
public interface DeptFeignService {
    @RequestMapping("getList")
     String getList();



}
