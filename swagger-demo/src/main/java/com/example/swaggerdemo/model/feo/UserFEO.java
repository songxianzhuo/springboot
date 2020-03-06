package com.example.swaggerdemo.model.feo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/12/25
 **/
@Data
@ApiModel(value = "添加用户对象",description = "添加用户时使用")
public class UserFEO {
    /**
     * @ApiModel()
     * 用于类，表示对类进行说明，用于参数用实体类接收
     * 属性：value–表示对象名，默认是类名
     *      description–描述
 *     @ApiModelProperty()
     * 用于方法，字段； 表示对model属性的说明或者数据操作更改
     * 属性：value–字段说明
     *      name–重写属性名字
     *      dataType–重写属性类型
     *      required–是否必填
     *      example–举例说明
     *      hidden–隐藏
     */

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空")
    @ApiModelProperty(name = "name",value = "姓名", example = "宋献卓",required = true,dataType = "String")
    private String name;
    /**
     * 年龄
     */
    @ApiModelProperty(name = "name",value = "姓名", example = "29",dataType = "Integer")
    private Integer age;
    /**
     * 手机号码
     */
    @NotNull(message = "手机号码不能为空")
    @ApiModelProperty(name = "name",value = "姓名", example = "18310556635",required = true,dataType = "String")
    private String phone;
    /**
     * 性别,1-男，2-女
     */
    @ApiModelProperty(name = "name",value = "姓名", example = "1",dataType = "Integer")
    private Integer sex;
}
