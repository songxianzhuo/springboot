package com.example.javademo.java8;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/29
 **/
public interface Person {
    /**
     * 通过接口名直接调用静态方法即可，和类的静态方法一样
     * @return
     */
    static String getName1(){
        return "sognxkianzhuo";
    }

    /**
     * 实现类对接口的default方法进行了默认的实现
     * 同时允许子类重写default方法
     * 如果实现类同时继承了一个父类且父类的方法名和default方法名相同，则优先继承父类的同名方法，即类优先
     * @return
     */
    default String getName2(){
        return "zhangyao";
    }
}
