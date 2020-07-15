package com.example.javademo.multithreading;

import com.example.javademo.JavaDemoApplicationTests;
import com.example.javademo.multithreading.pc.Consumer;
import com.example.javademo.multithreading.pc.Producer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/11
 **/
public class ThreadTest extends JavaDemoApplicationTests {

    @SneakyThrows
    @Test
    public void test(){
        for (int i = 0; i < 50 ; i++) {
            new MyThread().start();
        }
    }

    /**
     * 单个消费者和单个生产者
     */
    @Test
    public void test2(){
        Object lock = new Object();
        Producer p = new Producer(lock);
        Consumer c = new Consumer(lock);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                p.proA();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                c.conA();
            }
        }).start();
    }

}
