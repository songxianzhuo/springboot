package com.example.ratelimiter.controller;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/12/9 11:03
 * @Version: 1.0
 */
@RestController
public class TestController {


    @Autowired
    public RateLimiter rateLimiter;

    @GetMapping(value = "/test")
    public Boolean testRateLimiter(){
        if(rateLimiter.tryAcquire()){
            // 业务逻辑
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

}
