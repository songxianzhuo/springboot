package com.example.javademo.multithreading.pc;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/13
 **/
public class ConsumerRunnable implements  Runnable {
    private Consumer c;

    public ConsumerRunnable(Consumer c) {
        this.c = c;
    }

    @Override
    public void run() {
        while (true) {
//            c.conA();
            c.conB();
        }
    }
}
