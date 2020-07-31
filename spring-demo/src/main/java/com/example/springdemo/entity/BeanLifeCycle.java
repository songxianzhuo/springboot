package com.example.springdemo.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringValueResolver;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletContext;

/**
 * Bean的生命周期
 * 接口的顺序即为对应方法的先后顺序
 * 链接：https://www.cnblogs.com/javazhiyin/p/10905294.html
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/30
 **/
public class BeanLifeCycle implements BeanNameAware,BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware,EmbeddedValueResolverAware,ResourceLoaderAware,
        ApplicationEventPublisherAware,MessageSourceAware, ApplicationContextAware, BeanPostProcessor, ServletContextAware, InitializingBean, DisposableBean {

    /**
     * 如果Bean实现了BeanNameAware接口的话，Spring将Bean的Id传递给setBeanName()方法
     * @param s
     */
    @Override
    public void setBeanName(String s) {
        System.out.println("BeanNameAware:setBeanName");
    }

    /**
     * 如果Bean实现了BeanFactoryAware接口的话，Spring将调用setBeanFactory()方法，将BeanFactory容器实例传入,用来获取其他bean
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware:setBeanFactory");
    }

    /**
     * 如果Bean实现了ApplicationContextAware接口的话，Spring将调用Bean的setApplicationContext()方法，将bean所在应用上下文引用传入进来
     * 在普通Bean对象生成之后调用，在InitializingBean.afterPropertiesSet之前调用或者用户自定义初始化方法之前。在ResourceLoaderAware.setResourceLoader，ApplicationEventPublisherAware.setApplicationEventPublisher，MessageSourceAware之后调用。
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware:setApplicationContext");
    }

    /**
     * 如果Bean实现了BeanPostProcessor接口，Spring就将调用它的预初始化方法postProcessBeforeInitialization()。
     * 将此BeanPostProcessor 应用于给定的新bean实例 在任何bean初始化回调方法(像是InitializingBean.afterPropertiesSet或者自定义的初始化方法）之前调用。这个bean将要准备填充属性的值。返回的bean示例可能被普通对象包装，默认实现返回是一个bean。
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor:postProcessBeforeInitialization");
        return null;
    }

    /**
     * 如果Bean 实现了BeanPostProcessor接口，Spring就将调用它的初始化后方法postProcessAfterInitialization()。
     * 将此BeanPostProcessor 应用于给定的新bean实例 在任何bean初始化回调方法(像是InitializingBean.afterPropertiesSet或者自定义的初始化方法)之后调用。这个bean将要准备填充属性的值。返回的bean示例可能被普通对象包装
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor:postProcessAfterInitialization");
        return null;
    }

    /**
     * 如果Bean 实现了InitializingBean接口，Spring将调用它的afterPropertiesSet()方法。
     * 被BeanFactory在设置所有bean属性之后调用(并且满足BeanFactory 和 ApplicationContextAware)。
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean:afterPropertiesSet");
    }

    /**
     * 如果bean使用init-method声明了初始化方法，该方法在InitializingBean的afterPropertiesSet()方法之后调用
     */
    public void customInit(){
        System.out.println("BeanLifeCycle:customInit");
    }

    /**
     * 如果bean使用destory-method声明了初始化方法，该方法在DisposableBean的destroy()方法之后调用
     */
    public void customDestory(){
        System.out.println("BeanLifeCycle:customDestory");
    }

    /**
     * 容器关闭时，如果bean实现了DisposableBean接口，Spring将调用它的destory()接口方法
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean:destroy");
    }

    /**
     * 注解@PostConstruct标记的方法会在InitializingBean的afterPropertiesSet()方法之前调用
     */
    @PostConstruct
    public void postConstruct(){
        System.out.println("BeanLifeCycle:customInit2");
    }

    /**
     * 注解@PreDestroy标记的方法会在DisposableBean的destroy()方法之前调用
     */
    @PreDestroy
    public void preDestroy(){
        System.out.println("BeanLifeCycle:customDestory2");
    }

    /**
     * 在普通属性设置之后，InitializingBean.afterPropertiesSet()之前调用
     * @param classLoader
     */
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("BeanClassLoaderAware:setBeanClassLoader");
    }

    /**
     * 在普通bean属性之后调用，在初始化调用afterPropertiesSet 或者自定义初始化方法之前调用。在 ApplicationContextAware 之前调用。
     * @param applicationEventPublisher
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("ApplicationEventPublisherAware:setApplicationEventPublisher");
    }

    /**
     * 在普通bean对象之后调用，在afterPropertiesSet 或者自定义的init-method 之前调用，在 ApplicationContextAware 之前调用。
     * @param resourceLoader
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("ResourceLoaderAware:setResourceLoader");
    }

    /**
     * 设置StringValueResolver 用来解决嵌入式的值域问题
     * @param stringValueResolver
     */
    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        System.out.println("EmbeddedValueResolverAware:setEmbeddedValueResolver");
    }

    /**
     * 设置environment在组件使用时调用
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("EnvironmentAware:setEnvironment");
    }

    /**
     * 在普通bean属性之后调用，在初始化调用afterPropertiesSet 或者自定义初始化方法之前调用，在 ApplicationContextAware 之前调用。
     * @param messageSource
     */
    @Override
    public void setMessageSource(MessageSource messageSource) {
        System.out.println("MessageSourceAware:setMessageSource");
    }

    /**
     * ServletContextAware.setServletContext(): 运行时设置ServletContext，在普通bean初始化后调用，在InitializingBean.afterPropertiesSet之前调用，在 ApplicationContextAware 之后调用注：是在WebApplicationContext 运行时
     * @param servletContext
     */
    @Override
    public void setServletContext(ServletContext servletContext) {
        System.out.println("ServletContextAware:setServletContext");
    }

    public String test(){
        return "songxianzhuo";
    }
}
