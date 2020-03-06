package com.example.javadocdemo.model.feo;


import javax.validation.constraints.NotNull;

/**
 * 用户对象FEO
 * @author SONG
 * @since 2019/12/25
 * @version 1.0.0
 **/
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
