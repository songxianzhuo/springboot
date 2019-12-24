package com.example.swaggerdemo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: User
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/12/23 22:48
 * @Version: 1.0
 */
@Data
@ApiModel(value = "user演示",description = "用户请求示例")
public class User {

    /**
     * @ApiModelProperty
     *
     *
     */


    @NotNull
    @ApiModelProperty(name = "名字",value = "songxianzhuo",example ="mockStrValue" )
    private String name;
    @NotNull
    @ApiModelProperty(name = "电话",required = true)
    private String phone;
    //@ApiModelProperty(name = "年龄",value = "12")
    private Integer age;
    @ApiModelProperty(value = "男",example = "男士")
    private String sex;

}
