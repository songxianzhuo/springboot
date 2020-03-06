package com.example.redisdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/01/08
 **/
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);
        // 设置序列化Key的实例化对象
        template.setKeySerializer(new StringRedisSerializer());
        // 设置序列化Value的实例化对象
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 设置序列化HashKey的实例化对象
        template.setHashKeySerializer(new StringRedisSerializer());
        // 设置序列化HashValue的实例化对象
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启redis事务，默认是false
        template.setEnableTransactionSupport(Boolean.TRUE);
        template.afterPropertiesSet();
        return template;
    }

}
