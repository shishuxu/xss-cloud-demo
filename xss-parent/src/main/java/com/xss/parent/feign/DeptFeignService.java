package com.xss.parent.feign;

import com.xss.parent.constant.AppConstant;
import com.xss.parent.fallback.DeptFallbackFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
@Component
@FeignClient(name = AppConstant.ORDER, fallbackFactory = DeptFallbackFactory.class)
public interface DeptFeignService {
//    开启负载均衡
    @LoadBalanced
    @RequestMapping("api/dept/getList")
     String getList();


}
