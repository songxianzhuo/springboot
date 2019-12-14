package com.example.redislimiter.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @ClassName: VoteUtil
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/12/6 15:56
 * @Version: 1.0
 */
@Component
public class VoteUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "limitScript")
    private DefaultRedisScript<Long> limitScript;

    private final static Integer MINUTES_LIMIT = 1;

    public Boolean vote(String voter, String player) {
        boolean success = false;
        long end = System.currentTimeMillis();
        long start = end - MINUTES_LIMIT * 60 * 1000;
//        redisTemplate.opsForZSet().add(player, "v3", System.currentTimeMillis());
//        Long count = redisTemplate.opsForZSet().count(player, start, end);
//        System.out.println(count);
//        Set<Object> p1 = redisTemplate.opsForZSet().rangeByScore(player, start, end);
//        assert p1 != null;
//        p1.forEach(System.out::println);
//        System.out.print(player + " " + start + " " + end + " 5 " + voter + " ");
        Long execute = redisTemplate.execute(limitScript, Collections.singletonList(player), start, end, 5, voter + end);
        System.out.println(execute);
        if (execute != null && execute > 0) {
            success = true;
        }
        return success;
    }

    /**
     * 投票限流 lua 脚本
     */
//    @Bean(name = "limitScript")
//    public DefaultRedisScript<Long> limitScript() {
//        String script = "if redis.call('zcount', KEYS[1], ARGV[1], ARGV[2]) < tonumber(ARGV[3]) then " +
//                "return redis.call('zadd', KEYS[1], ARGV[2], ARGV[4]) " +
//                "else " +
//                "return 0 end";
//        DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>();
//        defaultRedisScript.setResultType(Long.class);
//        defaultRedisScript.setScriptText(script);
//        return defaultRedisScript;
//    }
}
