package com.xss.parent.fallback;

import com.xss.parent.feign.GoodsFeignService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GoodsFallbackFactory implements FallbackFactory<GoodsFeignService> {
    @Override
    public GoodsFeignService create(Throwable cause) {
        return isException -> null;
    }
}
