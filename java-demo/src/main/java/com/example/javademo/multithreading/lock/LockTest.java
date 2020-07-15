package com.example.javademo.multithreading.lock;

import lombok.SneakyThrows;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/15
 **/
public class LockTest {

    @SneakyThrows
    public static void main(String[] args) {
        LockService lockService = new LockService();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockService.methodA();
            }
        });
        thread1.start();
        Thread.sleep(100);
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockService.methodB();
            }
        });
        thread2.start();
        System.out.println(thread1.getState());
        System.out.println(thread2.getState());
    }
}
