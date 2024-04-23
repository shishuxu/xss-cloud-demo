package com.xss.parent.feign;

import com.xss.parent.constant.AppConstant;
import com.xss.parent.fallback.GoodsFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = AppConstant.SEATA_ORDER, fallbackFactory = GoodsFallbackFactory.class)
public interface GoodsFeignService {

    @RequestMapping("/api/goods/add")
    String add(@RequestParam(value = "isException") boolean isException) ;
}
