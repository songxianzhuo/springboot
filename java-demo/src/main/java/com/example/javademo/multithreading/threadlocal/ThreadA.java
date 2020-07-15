package com.example.javademo.multithreading.threadlocal;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/14
 **/
public class ThreadA extends Thread {

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("在ThreadA线程中取值=" + Tools.threadLocalExt.get());
            Thread.sleep(100);
        }
        Thread.sleep(5000);
        System.out.println("在ThreadA线程中2次取值=" + Tools.threadLocalExt.get());
    }

    @SneakyThrows
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("在main线程中取值=" + Tools.threadLocalExt.get());
            Thread.sleep(100);
        }
        new ThreadA().start();
        Thread.sleep(2000);
        Tools.threadLocalExt.set(1111111111L);
        Thread.sleep(5000);
        new ThreadB().start();
        System.out.println("在main线程中取值=" + Tools.threadLocalExt.get());
    }
}
