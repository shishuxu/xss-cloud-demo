package com.xss.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/config")
@RefreshScope
public class NacosConfigController {

        @Value("${useLocalCache:false}")
        private boolean useLocalCache;

        @RequestMapping("/get")
        public boolean get() {
            return useLocalCache;
        }
    }
