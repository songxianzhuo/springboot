package com.example.redisdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/01/19
 **/

@RestController
public class TestController {


    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    public TestController(RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }


    @GetMapping(value = "/testRedis")
    public Object testRedis(){
        redisTemplate.multi();
        redisTemplate.opsForValue().set("a","qazwsx1");
        redisTemplate.opsForValue().set("b","edcrfv1");
        redisTemplate.exec();
        return null;
    }
}
