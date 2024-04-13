package com.xss.parent.fallback;

import com.xss.parent.feign.DeptFeignService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeptFallbackFactory implements FallbackFactory<DeptFeignService> {
    @Override
    public DeptFeignService create(Throwable cause) {
        return () -> "null";
    }
}
