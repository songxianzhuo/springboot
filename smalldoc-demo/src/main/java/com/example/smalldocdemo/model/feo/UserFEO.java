package com.example.smalldocdemo.model.feo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/12/25
 **/
@Data
public class UserFEO {

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空")
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 手机号码
     */
    @NotNull(message = "手机号码不能为空")
    private String phone;
    /**
     * 性别,1-男，2-女
     */
    private Integer sex;
}
