package com.example.javademo.multithreading.pc;

import lombok.SneakyThrows;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/13
 **/
public class Producer {

    private Object lock;

    public Producer(Object lock) {
        this.lock = lock;
    }

    /**
     * 单个生产者
     */
    @SneakyThrows
    public void proA(){
        synchronized (lock) {
            if(!ValueObject.value.equals("")){
                System.out.println("生产者" + Thread.currentThread().getName() + " WAITING 了");
                lock.wait();
            }
            ValueObject.value = String.valueOf(ValueObject.atomicInteger.incrementAndGet());
            lock.notify();
        }
    }

    /**
     * 多个生产者
     */
    @SneakyThrows
    public void proB(){
        synchronized (lock) {
            while(!ValueObject.value.equals("")){
                System.out.println("生产者" + Thread.currentThread().getName() + " WAITING 了");
                lock.wait();
            }
            ValueObject.value = String.valueOf(ValueObject.atomicInteger.incrementAndGet());;
            // notify()在多生产者和多消费者的情况下，会导致消费者通知给消费者，或生产者通知给生产者，从而导致所有线程都处于waiting状态，造成系统假死
//            lock.notify();
            lock.notifyAll();
        }
    }
}
