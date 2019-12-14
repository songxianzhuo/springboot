package com.example.ratelimiter.service;

import com.example.ratelimiter.RatelimiterApplicationTests;
import com.google.common.util.concurrent.RateLimiter;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: TestSmoothWarmingUpService
 * @Description: 平滑预热限流
 * @Author: SONG
 * @Date: 2019/12/9 11:33
 * @Version: 1.0
 */
public class TestSmoothWarmingUpService extends RatelimiterApplicationTests {

    /**
     * 链接：https://www.jianshu.com/p/280bf2dbd6f0
     * RateLimiter的 SmoothWarmingUp是带有预热期的平滑限流，它启动后会有一段预热期，逐步将分发频率提升到配置的速率。
     */

    /**
     *
     */
    @Test
    public void testSmoothWarmingUp1(){
        System.out.println(System.currentTimeMillis());
        RateLimiter r = RateLimiter.create(2, 3, TimeUnit.SECONDS);
//        RateLimiter r = RateLimiter.create(2, Duration.ofSeconds(3));
        System.out.println(System.currentTimeMillis());
        while (true) {
            // 前三次时间相加正好等于预热期，预热几秒，前几次预设时间相加正好等于预热期
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            //之后正常速率获取令牌
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("end");
        }
    }

}
