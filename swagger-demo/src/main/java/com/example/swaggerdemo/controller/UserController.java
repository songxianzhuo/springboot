package com.example.swaggerdemo.controller;

import com.example.swaggerdemo.model.feo.UserFEO;
import com.example.swaggerdemo.model.vo.UserVO;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/12/23 19:29
 * @Version: 1.0
 */
@Api(tags = "用户相关接口")
@RestController
public class UserController {

    /**
     * 相关注解说明
     * @Api()
     * 用于类；表示标识这个类是swagger的资源
     * 属性：tags:说明，如果有多个tags,则会以list形式展示
     *      value: 说明，可以使用tags替代
     * @ApiOperation()
     * 用于方法；表示一个http请求的操作
     * 属性：value: 用于方法描述
     *      notes: 用于提示内容
     *      tags: 可以重新分组
     * @ApiParam()
     * 用于方法，参数，字段说明；表示对参数的添加元数据（说明或是否必填等）
     * 属性：name:参数名
     *      value:参数说明
     *      required:是否必填
     *      example:举例说明
     * @ApiImplicitParam()
     * 用于方法，表示单独的请求参数
     * 属性：name–参数名
     *      value–参数说明
     *      dataType–数据类型，默认是string，如果是对象，必须和@ApiModel的value属性保持一致
     *      dataTypeClass-数据类型的Class属性
     *          注意：dataType 默认是string，有int，long，array，map等类型，用int或long时，因为参数默认值是string类型，所以转换会报错
 *                  非string类型的基本数据类型（如int，long，double、float等）传参时，建议使用dataTypeClass属性，
     *      paramType–参数类型，表示参数放在那里
     *          header-->请求参数的获取：@RequestHeader(代码中接收注解)
     *          query-->请求参数的获取：@RequestParam(代码中接收注解)
     *          path（用于restful接口）-->请求参数的获取：@PathVariable(代码中接收注解)
     *          body-->请求参数的获取：@RequestBody(代码中接收注解)
     *          form（不常用）
     *      example–举例说明
     *      allowMultiple-表示是数组格式的参数
     *      allowMultiple-表示是数组格式的参数
     * @ApiImplicitParams()
     * 用于方法，包含多个 @ApiImplicitParam
     * @ApiIgnore()
     * 用于类或者方法上，可以不被swagger显示在页面上
     *
     */

    /**
     * 一个路径参数的接口示例
     * @param id
     * @return
     */
    @ApiOperation(value = "查询用户信息",notes = "根据id查询用户信息")
    @ApiImplicitParam(name = "id",value = "用户Id",required = true,dataTypeClass = Integer.class,paramType = "path")
    @GetMapping(value = "/getUserById/{id}")
    public Object getUserById(@PathVariable(name = "id") Integer id){
        UserVO userVO = new UserVO();
        userVO.setId(id);
        userVO.setName("宋献卓");
        userVO.setSex(1);
        userVO.setAge(new Random().nextInt(30));
        return userVO;
    }

    /**
     * 一个请求参数的接口示例
     * @param id
     * @return
     */
    //@ApiIgnore
    @ApiOperation(value = "查询用户信息",notes = "根据id查询用户信息")
    @ApiImplicitParam(name = "id",value = "用户Id",required = true,dataTypeClass = Integer.class,paramType = "query",defaultValue = "1")
    @GetMapping(value = "/getUserById2")
    public Object getUserById2(@RequestParam Integer id){
        UserVO userVO = new UserVO();
        userVO.setId(id);
        userVO.setName("宋献卓");
        userVO.setSex(1);
        userVO.setAge(new Random().nextInt(30));
        return userVO;
    }

    /**
     * 两个请求参数的接口示例
     * @param name
     * @param sex
     * @return
     */
    @ApiOperation(value = "查询用户信息", notes = "根据用户名和性别查询用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户姓名", required = true, dataType = "String",example = "宋献卓",paramType = "query"),
            @ApiImplicitParam(name = "sex", value = "性别(1-男，2-女)", required = false, dataType = "int",example = "1",paramType = "query")
    })
    @GetMapping(value = "/getUserByNameAndSex")
    public Object getUserByNameAndSex(@RequestParam(name = "name") String name,
                                      @RequestParam(name = "sex",defaultValue = "1",required = false) Integer sex) {
        UserVO userVO = new UserVO();
        userVO.setId(1);
        userVO.setName(name);
        userVO.setSex(sex);
        userVO.setAge(new Random().nextInt(30));
        return userVO;
    }

    /**
     * 一个请求对象的接口示例
     */
    @ApiOperation(value = "添加用户", notes = "添加用户提示")
    @ApiImplicitParam(name = "userFEO", value = "添加用户时数据对象", required = true, dataType = "添加用户对象",paramType = "body")
    @PostMapping(value = "/addUser")
    public Object addUser(@RequestBody UserFEO userFEO) {
        /**
         * 中间BO，PO转换省略
         */
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userFEO,userVO);
        userVO.setId(new Random().nextInt(100));
        return userVO;
    }

    /**
     * 一个请求数组的接口示例
     */
    @ApiOperation(value = "批量查询用户", notes = "通过id数组查询用户")
    @ApiImplicitParam(name = "ids", value = "用户id数组", required = true, dataType = "String",paramType = "query",allowMultiple = true)
    @PostMapping(value = "/getUserByIds")
    public Object getUserByIds(@RequestParam List<String> ids) {
        System.out.println(ids);
        UserVO userVO = new UserVO();
        userVO.setId(1);
        userVO.setName("songxianzhuo");
        userVO.setSex(1);
        userVO.setAge(new Random().nextInt(30));
        return userVO;
    }

}
