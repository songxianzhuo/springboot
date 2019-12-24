package com.example.swaggerdemo.controller;

import com.example.swaggerdemo.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/12/23 19:29
 * @Version: 1.0
 */
@Api(value = "用户Controller",tags = "测试类")
@RestController
public class UserController {

    /**
     * @Api
     * 用于类；表示标识这个类是swagger的资源
     * 属性：tags:说明，如果有多个tags,则会以list形式展示
     *      value: 说明，可以使用tags替代
     * @ApiOperation
     * 用于方法；表示一个http请求的操作
     * 属性：value: 用于方法描述
     *      notes: 用于提示内容
     *      tags: 可以重新分组
     * @ApiParam
     * 用于方法，参数，字段说明；表示对参数的添加元数据（说明或是否必填等）
     * 属性：name:参数名,默认同方法参数名
     *      value:参数说明
     *      required:是否必填
     */

    @ApiOperation(value = "获取用户")
    @GetMapping(value = "/getUser")
    public Object getUser(@ApiParam(value = "姓名值",required = true) String name){
        return null;
    }


    @ApiOperation(value = "添加用户")
    @PostMapping(value = "/addUser")
    public Object addUser(User user){
        return null;
    }
}
