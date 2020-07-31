package com.example.proxydemo.service.impl;

import com.example.proxydemo.model.User;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/24
 **/
public class UserService {

    public Boolean save(User user) {
        System.out.println("----用户已保存!----");
        return Boolean.TRUE;
    }

    public final Boolean delete(Long id){
        System.out.println("----用户已删除!----");
        return Boolean.TRUE;
    }
}
