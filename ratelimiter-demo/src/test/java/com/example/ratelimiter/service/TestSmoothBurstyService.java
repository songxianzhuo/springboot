package com.example.ratelimiter.service;

import com.example.ratelimiter.RatelimiterApplicationTests;
import com.google.common.util.concurrent.RateLimiter;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: TestSmoothBurstyService
 * @Description: 平滑突发限流
 * @Author: SONG
 * @Date: 2019/12/9 11:24
 * @Version: 1.0
 */
public class TestSmoothBurstyService extends RatelimiterApplicationTests {

    /**
     * 平滑突发限流
     * SmoothBursty
     * 先来者预消费，后来者买单/前人挖坑后人跳
     * 原理：RateLimiter的原理就是每次调用 acquire时用当前时间和 nextFreeTicketMicros进行比较，
     *      根据二者的间隔和添加单位令牌的时间间隔 stableIntervalMicros来刷新存储令牌数 storedPermits。
     *      然后acquire会进行休眠，直到 nextFreeTicketMicros 获取令牌返回
     *
     * 由于RateLimiter允许预消费，上次请求预消费令牌后，下次请求需要等待相应的时间到nextFreeTicketMicros时刻才可以获取令牌
     *
     * 注意：
     * permitsPerSecond:既是令牌生成速率，也是桶中令牌最大值
     *                  但是预消费令牌数可以大于permitsPerSecond
     * stopwatch：是一个从限流器生成时开始的计时器
     *
     * 链接：https://www.jianshu.com/p/bb73114bcf9e
     */
    @Test
    public void testSmoothBursty(){
        RateLimiter r = RateLimiter.create(1);
        while(true){
            // acquire函数如下所示，它会调用 reserve函数计算获取目标令牌数所需等待的时间，然后使用 SleepStopwatch进行休眠，最后返回等待时间
            System.out.println("get 1 tokens: " + r.acquire() + "s");
        }
    }

    /**
     * RateLimiter使用令牌桶算法，会进行令牌的累积，如果获取令牌的频率比较低，则不会导致等待，直接获取令牌
     */
    @Test
    public void testSmoothBursty2(){
        RateLimiter r = RateLimiter.create(5);
        while(true){
            System.out.println("get 1 tokens: " + r.acquire() + "s");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("get 1 tokens: " + r.acquire(2) + "s");
            System.out.println("get 1 tokens: " + r.acquire(3) + "s");
            System.out.println("get 1 tokens: " + r.acquire(4) + "s");
            System.out.println("end");
        }
    }


    /**
     * RateLimiter由于会累积令牌，所以可以应对突发流量
     * RateLimiter在没有足够令牌发放时，采用滞后处理的方式，也就是前一个请求获取令牌所需等待的时间由下一次请求来承受，也就是代替前一个请求进行等待
     */
    @Test
    public void testSmoothBursty3(){
        RateLimiter r = RateLimiter.create(5);
        while(true){
            System.out.println("get 1 tokens: " + r.acquire(5) + "s");
            // 滞后效应，需要替前一个请求进行等待
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("end");
        }
    }

    /**
     * 尝试获取令牌、
     * 当前线程尝试获取令牌，跟获取的令牌数无关，跟timeout的大小有关
     */
    @Test
    public void testSmoothBursty4(){
        RateLimiter r = RateLimiter.create(10);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 尝试获取令牌,在timeout时间内能拿到令牌则返回true,否则返回false
        System.out.println(r.tryAcquire());
        System.out.println(r.tryAcquire(1));
//        System.out.println(r.tryAcquire(Duration.ofSeconds(1)));
//        System.out.println(r.tryAcquire(3, TimeUnit.SECONDS));
//        System.out.println(r.tryAcquire(10,Duration.ofSeconds(1)));
//        System.out.println(r.tryAcquire(3,3, TimeUnit.SECONDS));
    }

    @Test
    public void testSmoothBursty5(){
        RateLimiter rateLimiter = RateLimiter.create(50);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        Runnable runnable = ()->{
            if(!rateLimiter.tryAcquire(1,100,TimeUnit.MILLISECONDS)){
                System.out.println("F"+Thread.currentThread().getName() + " time:" + System.currentTimeMillis());
            }else {
                System.out.println("A"+Thread.currentThread().getName() + " time:" + System.currentTimeMillis());
            }
        };
        for (int i = 0; i < 20; i++) {
            executorService.execute(runnable);
        }
        try {
            executorService.awaitTermination(1,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
