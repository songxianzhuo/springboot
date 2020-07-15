package com.example.javademo.multithreading.timer;

import lombok.SneakyThrows;

import java.util.Date;
import java.util.TimerTask;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/15
 **/
public class MyTimeTask2 extends TimerTask {
    @SneakyThrows
    @Override
    public void run() {
        System.out.println("B任务执行，开始时间：" + new Date());
        System.out.println("B任务执行，结束时间：" + new Date());
    }
}
