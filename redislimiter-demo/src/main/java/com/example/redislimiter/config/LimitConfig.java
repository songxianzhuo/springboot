package com.example.redislimiter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;

/**
 * @ClassName: LimitConfig
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/12/6 15:45
 * @Version: 1.0
 */
@Configuration
public class LimitConfig {

    /**
     * 注入 lua 脚本
     */
    @Bean
    public DefaultRedisScript<Long> limitScript() {
        DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setLocation(new ClassPathResource("limit_script.lua"));
        defaultRedisScript.setResultType(Long.class);
        return defaultRedisScript;
    }

}
