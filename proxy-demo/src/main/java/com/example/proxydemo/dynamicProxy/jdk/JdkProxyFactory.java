package com.example.proxydemo.dynamicProxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 创建jdk动态代理 利用jdk的反射机制生成代理对象
 *
 * 只能对接口进行代理，代理对象不需要有接口实现，但是目标对象需要有接口实现，否则不能使用jdk动态代理
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/24
 **/
public class JdkProxyFactory {

    // 目标对象
    private Object target;

    public JdkProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 给目标对象生成代理对象
     *
     * ClassLoader loader：指定当前目标对象使用类加载器,获取加载器的方法是固定的
     * Class<?>[] interfaces：指定目标对象实现的接口的类型,使用泛型方式确认类型
     * InvocationHandler h：事件处理,执行目标对象的方法时,会触发事件处理器的方法,会把当前执行目标对象的方法作为参数传入
     * @return
     */
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("JdkProxy-开始事务...");
                        //运用反射执行目标对象方法
                        Object returnValue = method.invoke(target, args);
                        System.out.println("JdkProxy-提交事务...");
                        return returnValue;
                    }
                });
    }
}
