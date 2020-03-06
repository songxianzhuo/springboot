package com.example.redisdemo.test;

import com.example.redisdemo.RedisdemoApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/01/20
 **/
public class TestRedisTemplate extends RedisdemoApplicationTests {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * redisTemplate操作redis事务
     *
     * 需配置redisTemplate的支持redis事务属性，默认是false
     */
    @Test
    public void test01(){
        // 加上监听锁，监听一个或多个key,若在exec命令执行前，监听的key发生变动，则事务被打断
        redisTemplate.watch("a");
        redisTemplate.multi();
        redisTemplate.opsForValue().set("a","qazwsx1");
        redisTemplate.opsForValue().set("b","edcrfv1");
        // 无论事务最终成功与否，都会释放监听锁
        redisTemplate.exec();
        // 释放监听锁，取消对key的监听
        redisTemplate.unwatch();
    }

    /**
     * SessionCallback操作redis事务
     *
     * 在redisTemplate不开启支持redis事务的前提下，可以使用SessionCallback操作redis事务，
     * 保证所有的操作都在同一个 Session 中完成
     */
    @Test
    public void test02(){
        SessionCallback sessionCallback = new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.multi();
                redisTemplate.opsForValue().set("a","20200101");
                redisTemplate.opsForValue().set("b","20200102");
                return redisOperations.exec();
            }
        };
        redisTemplate.execute(sessionCallback);
    }
}
