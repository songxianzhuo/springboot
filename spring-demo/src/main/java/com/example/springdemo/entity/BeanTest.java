package com.example.springdemo.entity;

import com.example.springdemo.config.BeanConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/30
 **/
public class BeanTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(BeanConfiguration.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String n:beanDefinitionNames)
        {
            System.out.println(n);
        }
        context.close();
    }
}
