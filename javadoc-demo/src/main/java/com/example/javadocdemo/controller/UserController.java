package com.example.javadocdemo.controller;

import com.example.javadocdemo.model.feo.UserFEO;
import com.example.javadocdemo.model.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * 用户相关接口
 * @author SONG
 * @since 2019/12/25
 * @version 1.0.0
 **/
@RestController
public class UserController {

    /**
     * 一个路径参数的接口示例
     * @param id 用户id
     * @author song
     * @return 用户对象
     */
    @GetMapping(value = "/getUserById/{id}")
    public UserVO getUserById(@PathVariable(name = "id") Integer id){
        UserVO userVO = new UserVO();
        userVO.setId(id);
        userVO.setName("宋献卓");
        userVO.setSex(1);
        userVO.setAge(new Random().nextInt(30));
        return userVO;
    }

    /**
     * 一个请求参数的接口示例
     * @param id 用户id
     * @author song
     * @return 用户对象
     */
    @GetMapping(value = "/getUserById2")
    public UserVO getUserById2(@RequestParam Integer id){
        UserVO userVO = new UserVO();
        userVO.setId(id);
        userVO.setName("宋献卓");
        userVO.setSex(1);
        userVO.setAge(new Random().nextInt(30));
        return userVO;
    }

    
    /**
     * 两个请求参数的接口示例
     * @param name 姓名
     * @param sex 性别（1-男，2-女）
     * @return 用户对象
     * @author song
     */
    @GetMapping(value = "/getUserByNameAndSex")
    public UserVO getUserByNameAndSex(@RequestParam(name = "name") String name,
                                      @RequestParam(name = "sex",defaultValue = "1",required = false) Integer sex) {
        UserVO userVO = new UserVO();
        userVO.setId(1);
        userVO.setName(name);
        userVO.setSex(sex);
        userVO.setAge(new Random().nextInt(30));
        return userVO;
    }

    /**
     * body请求示例
     * @param userFEO 用户添加对象
     * @return 用户对象
     * @author song
     */
    @PostMapping(value = "/addUser")
    public UserVO addUser(@RequestBody UserFEO userFEO) {
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
     * 大量非会计案例的烦恼
     * @param ids 用户id数组
     * @return 用户对象
     * @author song
     */
    @PostMapping(value = "/getUserByIds")
    public UserVO getUserByIds(@RequestParam List<String> ids) {
        System.out.println(ids);
        UserVO userVO = new UserVO();
        userVO.setId(1);
        userVO.setName("songxianzhuo");
        userVO.setSex(1);
        userVO.setAge(new Random().nextInt(30));
        return userVO;
    }
}
