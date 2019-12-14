package com.example.ratelimiter.config;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: RateLimiterConfig
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/12/9 17:41
 * @Version: 1.0
 */
@Configuration
public class RateLimiterConfig {

    @Bean
    public RateLimiter rateLimiter(){
        return RateLimiter.create(50);
    }
}
