package com.example.javademo.multithreading;

import lombok.SneakyThrows;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/13
 **/
public class ThreadService {

    private static Object lock = new Object();
    public static Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("wait begin time:" + System.currentTimeMillis());
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("wait end time:" + System.currentTimeMillis());
            }
        }
    };

    public static Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("wait(long) begin time:" + System.currentTimeMillis());
                try {
                    lock.wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("wait(long) end time:" + System.currentTimeMillis());
            }
        }
    };


    public static Runnable runnable3 = new Runnable() {
        @SneakyThrows
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("notify begin time:" + System.currentTimeMillis());
                lock.notify();
                System.out.println("notify end time:" + System.currentTimeMillis());
            }
        }
    };

    public static Runnable runnable4 = new Runnable(){
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("count begin time:" + System.currentTimeMillis());
                int count = 0;
                for (int i = 0; i < 100; i++) {
                    count++;
                }
                System.out.println("count end time:" + System.currentTimeMillis());
            }
        }
    };

    public static Runnable runnable5 = new Runnable(){
        @SneakyThrows
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("begin time:" + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("end time:" + System.currentTimeMillis());
            }
        }
    };

    @SneakyThrows
    public static void main(String[] args) {
        Thread thread1 = new Thread(runnable2);
        thread1.start();
        Thread.sleep(2000);
        for (int i = 0; i < 10; i++) {
            Thread thread2 = new Thread(runnable5);
            thread2.start();
        }


    }
}
