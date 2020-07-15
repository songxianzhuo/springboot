package com.example.javademo.multithreading.threadlocal;

import java.util.Date;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/14
 **/
public class ThreadLocalExt extends ThreadLocal<Long> {

    @Override
    protected Long initialValue() {
        return System.currentTimeMillis();
    }


}
