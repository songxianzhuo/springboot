package com.example.javademo.multithreading;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/11
 **/
public class MyThread extends Thread {

    volatile public static int count = 1;

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " before" +  " count=" + count);
            count++;
            System.out.println(Thread.currentThread().getName() + " end" +  " count=" + count);
        }
    }
}
