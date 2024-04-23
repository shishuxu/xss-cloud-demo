package com.xss.seata.controller;

import com.xss.seata.config.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁测试
 *
 * @author xss
 * @version 1.0
 * @date 2024/04/13
 */
@Slf4j
@RestController
@RequestMapping("api/redis/")
public class RedisTestController {
    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    @GetMapping(value = "/addOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object addOrder(@RequestParam(name = "num") int num) {
        Integer a = (Integer) redisService.get("num");
        int b = a - num;
        if (b > 0) {
            redisService.set("num", b);
        }
        System.out.println("库存" + b);
        return b;
    }

    /**
     * 分布式锁的简易实现
     */
    @GetMapping(value = "/testRedis", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object testRedis(@RequestParam(name = "num") int num, HttpServletResponse response) {
        String key = "lock_001";
        String threadId = UUID.randomUUID().toString();
        boolean check = redisTemplate.opsForValue().setIfAbsent(key, threadId, 10, TimeUnit.SECONDS);
        if (!check) {
            //设置成功则返回的是 ture
            response.setStatus(500);
            return "error----------------";
        }
        try {
            Integer a = (Integer) redisTemplate.opsForValue().get("num");
            int b = a - num;
            if (b > 0) {
                redisTemplate.opsForValue().set("num", b);
            }
            System.out.println("库存" + b);
            return b;

        } catch (Exception e) {

        } finally {
            if (threadId.equals(Objects.requireNonNull(redisTemplate.opsForValue().get(key)).toString())) {
                //如果是同一个线程，则删除锁（可能出现锁过期时，这里把别的线程的锁删除了，出现原子性问题）
                redisTemplate.delete(key);
            }
        }
        return "b";

    }

    /**
     * Redisson 分布式锁的简易实现
     */
    @GetMapping(value = "/testRedisson", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object testRedisson(@RequestParam(name = "num") int num, HttpServletResponse response) {
        String key = "lock_001";
        RLock lock = redissonClient.getLock(key);//非公平锁 ，随机取等待中的线程分配锁
//        RLock lock = redissonClient.getFairLock(key);//公平锁 ，按先后顺序分配锁
        try {
            if (lock.tryLock(50, 60, TimeUnit.SECONDS)) {
                Integer a = (Integer) redisTemplate.opsForValue().get("num");
                int b = a - num;
                if (b > 0) {
                    redisTemplate.opsForValue().set("num", b);
                }
                System.out.println("获取锁库存" + b);
                return b;
            }else {
                response.setStatus(500);
                System.out.println("没有获取锁");
                return "没有获取锁-------------";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            是锁定状态，并且由当前线程保持
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                // 釋放鎖
//                log.info("线程：" + Thread.currentThread().getName() + "释放锁");
                lock.unlock();
            }
        }
        return "b";

    }


}
