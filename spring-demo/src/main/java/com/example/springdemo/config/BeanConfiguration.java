package com.example.springdemo.config;

import com.example.springdemo.entity.BeanLifeCycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/30
 **/
@Configuration
public class BeanConfiguration {

    @Bean(initMethod = "customInit",destroyMethod = "customDestory")
    public BeanLifeCycle beanLifeCycle(){
        return new BeanLifeCycle();
    }
}
