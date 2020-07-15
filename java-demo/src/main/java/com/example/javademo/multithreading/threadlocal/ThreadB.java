package com.example.javademo.multithreading.threadlocal;

import lombok.SneakyThrows;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/14
 **/
public class ThreadB extends Thread {

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("在ThreadB线程中取值=" + Tools.threadLocalExt.get());
            Thread.sleep(100);
        }
        Thread.sleep(5000);
        System.out.println("在ThreadB线程中2次取值=" + Tools.threadLocalExt.get());
    }
}
