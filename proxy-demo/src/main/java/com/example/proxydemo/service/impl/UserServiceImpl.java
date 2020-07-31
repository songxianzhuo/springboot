package com.example.proxydemo.service.impl;

import com.example.proxydemo.model.User;
import com.example.proxydemo.service.IUserService;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/24
 **/
public class UserServiceImpl implements IUserService {

    @Override
    public Boolean save(User user) {
        System.out.println("----用户已保存!----");
        return Boolean.TRUE;
    }

}
