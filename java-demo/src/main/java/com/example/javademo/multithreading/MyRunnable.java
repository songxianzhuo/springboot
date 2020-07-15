package com.example.javademo.multithreading;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/15
 **/
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            System.out.println(i);
        }
    }
}
