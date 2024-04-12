package com.xss.parent.redisson;


import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author xss
 * @version 1.0.0
 * @date 2022-11-22 17:00
 */

@Data
@Configuration
@SuppressWarnings("all")
public class RedissonInit {

    @Bean
    public RedissonConfig RedissonInit() {
        return new RedissonConfig();
    }


    /**
     * 創建客戶端
     *
     * @param redissonConfig redis 配置
     * @return 客戶端
     */

    @Bean
    public RedissonClient redissonClient(RedissonConfig redissonConfig) {
        // 配置類
        Config config = new Config();
        // 添加redis地址
        config.useSingleServer().setAddress("redis://" + redissonConfig.getHost() + ":" + redissonConfig.getPort());
        // 添加redis密碼
        config.useSingleServer().setPassword(StringUtils.isEmpty(redissonConfig.getPassword()) ? null : redissonConfig.getPassword());
        //線程池數量 默认值: 当前处理核数量 * 2 这个线程池数量被所有RTopic对象监听器，RRemoteService调用者和RExecutorService任务共同共享
//        config.setThreads(2)
        //监控锁的看门狗超时，单位：毫秒  默认值：30000
//        config.setLockWatchdogTimeout(3000);
        return Redisson.create(config);
    }


}

