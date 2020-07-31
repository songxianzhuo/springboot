package com.example.proxydemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/24
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public Long uuid;

    public String name;

    public Integer age;

    public String sex;
}
