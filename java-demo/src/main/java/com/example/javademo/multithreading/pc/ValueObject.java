package com.example.javademo.multithreading.pc;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/13
 **/
public class ValueObject {

    public static String value = "";

    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    @SneakyThrows
    public static void main(String[] args) {
        Object lock = new Object();
        Producer p = new Producer(lock);
        Consumer c = new Consumer(lock);
        ProducerRunnable producerRunnable = new ProducerRunnable(p);
        ConsumerRunnable consumerRunnable = new ConsumerRunnable(c);
        for (int i = 0; i <2 ; i++) {
            new Thread(producerRunnable).start();
            new Thread(consumerRunnable).start();
        }
        Thread.sleep(5000);
        Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(threadArray);
        for (int i = 0; i < threadArray.length ; i++) {
            System.out.println("哈哈" + threadArray[i].getName() + " " + threadArray[i].getState());
        }
    }
}
