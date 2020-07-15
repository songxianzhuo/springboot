package com.example.javademo.multithreading.pc;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/13
 **/
public class ProducerRunnable implements Runnable {
    private Producer p;

    public ProducerRunnable(Producer p) {
        this.p = p;
    }

    @Override
    public void run() {
        while (true) {
//            p.proA();
            p.proB();
        }
    }
}
