package com.example.rabbitproducer.model;

import lombok.Data;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/03/12
 **/
@Data
public class Person {

    private String name;

    private Integer age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
