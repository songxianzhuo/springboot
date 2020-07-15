package com.example.javademo.multithreading.timer;

import lombok.SneakyThrows;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/15
 **/
public class TestTimer {

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("当前时间：" + new Date());
        Calendar calendar = Calendar.getInstance();
        Timer timer = new Timer();
        MyTimeTask myTimeTask = new MyTimeTask();
        timer.schedule(myTimeTask,calendar.getTime(),5000);
        calendar.add(Calendar.SECOND,5);
        MyTimeTask2 myTimeTask2 = new MyTimeTask2();
        timer.schedule(myTimeTask2,calendar.getTime(),3000);
        Thread.sleep(5000);
        myTimeTask.cancel();
    }
}
