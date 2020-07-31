package com.example.proxydemo.staticProxy;

import com.example.proxydemo.model.User;
import com.example.proxydemo.service.IUserService;

/**
 * 静态代理
 * 优点：在不修改目标对象的功能前提下。对目标对象进行功能扩展
 * 缺点：代理对象和目标对象会实现同一个接口，导致很多代理类，一旦接口发生改变（增加方法或减少方法），所有的目标对象和代理对象都要维护，维护成本高
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/24
 **/
public class UserServiceProxy implements IUserService {

    /**
     * 目标对象
     */
    private IUserService target;

    public UserServiceProxy(IUserService userService) {
        this.target = userService;
    }

    /**
     * 调用目标对象对应方法并对方法进行功能增强
     * @param user
     */
    @Override
    public Boolean save(User user) {
        System.out.println("StaticProxy-开始事务...");
        //执行目标对象的方法
        Boolean result = target.save(user);
        System.out.println("StaticProxy-提交事务...");
        return result;
    }

}
