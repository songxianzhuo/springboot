package com.example.javademo.multithreading;

import lombok.SneakyThrows;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/15
 **/
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
        Thread.sleep(200);
        for (int i = 0; i < 20; i++) {
            Thread thread2 = new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    System.out.println("2");
                    Thread.sleep(5000);
                }
            });
            thread2.setPriority(10);
            thread2.start();
        }
    }
}
