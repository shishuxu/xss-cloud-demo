package com.xss.parent.fallback;

import com.xss.parent.feign.TestNameFeignService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class TestNameFallbackFactory implements FallbackFactory<TestNameFeignService> {


    @Override
    public TestNameFeignService create(Throwable cause) {
        return () -> "null";
    }
}
