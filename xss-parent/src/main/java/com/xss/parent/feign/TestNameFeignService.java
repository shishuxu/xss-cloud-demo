package com.xss.parent.feign;

import com.xss.parent.constant.AppConstant;
import com.xss.parent.fallback.TestNameFallbackFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
@Component
@FeignClient(name = AppConstant.USER, fallbackFactory = TestNameFallbackFactory.class)
public interface TestNameFeignService {
    /**
     * 验证mybatis的批处理
     *开启负载均衡
     * @return 、、
     */
    @LoadBalanced
    @GetMapping(value = "api/testName/testJenkins")
    String test();

}
