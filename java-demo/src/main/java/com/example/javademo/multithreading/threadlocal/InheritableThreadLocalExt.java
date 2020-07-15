package com.example.javademo.multithreading.threadlocal;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/14
 **/
public class InheritableThreadLocalExt extends InheritableThreadLocal<Long> {

    @Override
    protected Long initialValue() {
        return System.currentTimeMillis();
    }

    @Override
    protected Long childValue(Long parentValue) {
        return parentValue / 10000;
    }
}
