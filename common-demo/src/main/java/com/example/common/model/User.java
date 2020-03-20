package com.example.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/03/11
 **/
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 8495034481401015236L;
    private String name;

    private Integer age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
