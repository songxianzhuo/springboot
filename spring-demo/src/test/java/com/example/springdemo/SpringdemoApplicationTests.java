package com.example.springdemo;

import com.example.springdemo.entity.BeanLifeCycle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class SpringdemoApplicationTests {

    @Autowired
    BeanLifeCycle beanLifeCycle;

    @Test
    void contextLoads() {
        System.out.println(beanLifeCycle.test());
    }



}
