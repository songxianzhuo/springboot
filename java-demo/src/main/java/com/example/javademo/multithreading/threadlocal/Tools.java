package com.example.javademo.multithreading.threadlocal;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/14
 **/
public class Tools {
    public static ThreadLocal threadLocalExt = new ThreadLocalExt();
    public static InheritableThreadLocal inheritableThreadLocalExt = new InheritableThreadLocalExt();
}
