package com.example.securitydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 继承WebSecurityConfigurerAdapter，并重写它的方法来设置一些web安全的细节
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/04/28
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 请求授权
     * spring security常用请求路径匹配器
     *      -antMatchers：使用Ant风格的路径拦截，如"/login","/home";
     *      -regexMatchers：使用正则表达式匹配路径
     *  资料：springboot实战344页
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 授权请求
            .authorizeRequests()
                // 路径为"/"和"/home"的可以任意访问
                .antMatchers("/", "/home").permitAll()
                // 其他请求必须经过身份认证
                .anyRequest().authenticated()
                .and()
                // 定义用户登录行为
            .formLogin()
                // 设置登录首页，默认是"/login",登录页面可以任意访问
                .loginPage("/login").permitAll()
                .and()
                // 定义注销行为
            .logout()
                // 设置注销路径，默认“/logout”,注销页面可以任意访问
                .logoutUrl("/logout").permitAll();
    }

    /**
     * 用户认证
     * 三种方式，来源：springboot实战343页
     *      -内存中添加用户
     *      -jdbc添加用户
     *      -实现UserDetailsService接口添加用户
     * 另外外 也可以使用 jdbcAuthentication()方法添加数据库中的用户
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // 在内存中添加用户，用户的名称为user，密码为password，用户角色为USER
                .inMemoryAuthentication()
                // 指定密码编码器，不配报错，默认是不是以明文进行密码匹配，添加后就可以以明文方式进行匹配
                .passwordEncoder(new MyPasswordEncoder())
                // 配置用户user
                .withUser("user")
                // 配置用户user密码
                .password("password")
                // 配置用户user角色
                .roles("USER");
    }
}
