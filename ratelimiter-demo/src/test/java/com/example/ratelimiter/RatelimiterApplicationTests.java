package com.example.ratelimiter;

import com.example.ratelimiter.controller.TestController;
import com.google.common.util.concurrent.RateLimiter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@SpringBootTest
public class RatelimiterApplicationTests {

    @Autowired
    public TestController testController;

    @Test
    void contextLoads() {
    }

    /**
     * 100并发访问
     */
    @Test
    public void testRateLimiter(){
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        Runnable runnable = ()->{
            if(testController.testRateLimiter()){
                System.out.println("A"+Thread.currentThread().getName() + " time:" + System.currentTimeMillis());
            }else {
                System.out.println("F"+Thread.currentThread().getName() + " time:" + System.currentTimeMillis());
            }
        };
        for (int i = 0; i < 20; i++) {
            executorService.execute(runnable);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            executorService.awaitTermination(1,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



}
