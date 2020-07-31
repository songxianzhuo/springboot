package com.example.javademo.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/29
 **/
public class TestInterface {
    public static StringBuffer stringBuffer = null;

    public static void main(String[] args) {
        System.out.println(Person.getName1());
        Student s = new Student();
        System.out.println(s.getName2());
        System.out.println(stringBuffer);

    }
}
