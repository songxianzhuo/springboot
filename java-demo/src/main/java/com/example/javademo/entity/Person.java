package com.example.javademo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/04/26
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;

    private Integer age;
}
