package com.example.jtaatomikos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @EnableTransactionManagement不是springboot的注解，springboot开始事务是自动配置的
 * 链接1：http://www.360doc.com/content/19/0117/16/10072361_809491235.shtml
 * 链接2：https://blog.csdn.net/qq_35890572/article/details/96286142
 */
//@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication
public class JtaatomikosApplication {

    public static void main(String[] args) {
        SpringApplication.run(JtaatomikosApplication.class, args);
    }

}
