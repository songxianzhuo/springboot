package com.example.swaggerdemo.model.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/12/25
 **/
@Data
public class UserVO {
    /**
     * 用户主键
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 性别,1-男，2-女
     */
    private Integer sex;
}
