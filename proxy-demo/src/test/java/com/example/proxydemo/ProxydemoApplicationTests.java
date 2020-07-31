package com.example.proxydemo;

import com.example.proxydemo.dynamicProxy.cglib.CglibProxyFactory;
import com.example.proxydemo.dynamicProxy.jdk.JdkProxyFactory;
import com.example.proxydemo.model.User;
import com.example.proxydemo.service.IUserService;
import com.example.proxydemo.service.impl.UserService;
import com.example.proxydemo.service.impl.UserServiceImpl;
import com.example.proxydemo.staticProxy.UserServiceProxy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProxydemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void staticProxy(){
        // 目标对象
        IUserService target = new UserServiceImpl();
        // 生成代理对象
        UserServiceProxy proxy = new UserServiceProxy(target);
        proxy.save(new User(1L,"StaticProxy",15,"男"));
    }

    @Test
    public void jdkProxy(){
        // 目标对象
        IUserService target = new UserServiceImpl();
//        UserService target = new UserService();
        // 生成代理对象
        IUserService proxy = (IUserService) new JdkProxyFactory(target).getProxyInstance();
//        UserService proxy = (UserService) new JdkProxyFactory(target).getProxyInstance();
        proxy.save(new User(1L,"JdkProxy",15,"男"));
    }

    @Test
    public void cglibProxy(){
        // 目标对象
        UserService target = new UserService();
        // 生成代理对象
        UserService proxy = (UserService) new CglibProxyFactory(target).getProxyInstance();
//        proxy.save(new User(1L,"CglibProxy",15,"男"));
        // final修饰的方法是不会被拦截的
        proxy.delete(1L);
    }

}
