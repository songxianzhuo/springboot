package com.example.javademo.multithreading.pc;

import lombok.SneakyThrows;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/13
 **/
public class Consumer {
    private Object lock;

    public Consumer(Object lock) {
        this.lock = lock;
    }

    /**
     * 单个消费者
     */
    @SneakyThrows
    public void conA(){
        synchronized (lock) {
            if(ValueObject.value.equals("")){
                System.out.println("消费者" + Thread.currentThread().getName() + " WAITING 了");
                lock.wait();
            }
            System.out.println("get的值：" + ValueObject.value);
            ValueObject.value = "";
            lock.notify();
        }
    }

    /**
     * 多个消费者
     */
    @SneakyThrows
    public void conB(){
        synchronized (lock) {
            while(ValueObject.value.equals("")){
                System.out.println("消费者" + Thread.currentThread().getName() + " WAITING 了");
                lock.wait();
            }
            System.out.println(Thread.currentThread().getName() + " get的值：" + ValueObject.value);
            ValueObject.value = "";
            // notify()在多生产者和多消费者的情况下，会导致消费者通知给消费者，或生产者通知给生产者，从而导致所有线程都处于waiting状态，造成系统假死
//            lock.notify();
            lock.notifyAll();
        }
    }
}
