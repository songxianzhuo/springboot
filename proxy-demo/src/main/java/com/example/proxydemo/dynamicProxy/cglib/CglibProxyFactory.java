package com.example.proxydemo.dynamicProxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib动态代理 也叫子类代理 底层是通过字节码处理框架ASN来实现
 * 基于继承的方式实现，不要求目标对象有接口实现，是在内存中构建一个目标对象的子类对象从而实现对目标对象的功能扩展
 * 注意：
 *      代理的类不能为final,否则报错
 *      目标对象的方法如果为final/static,那么就不会被拦截,即不会执行目标对象额外的业务方法
 *      如果方法为static,private则无法进行代理
 * 学习链接：https://www.cnblogs.com/qlqwjy/p/7550609.html，https://blog.csdn.net/wenbingoon/article/details/8988553
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/24
 **/
public class CglibProxyFactory {

    // 目标对象
    private Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance(){
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("CglibProxy-开始事务...");
                //运用反射执行目标对象方法
                Object returnValue = method.invoke(target, args);
                System.out.println("CglibProxy-提交事务...");
                return returnValue;
            }
        });
        //4.创建子类(代理对象)
        return en.create();
    }


}
