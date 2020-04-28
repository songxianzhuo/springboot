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
     * authorizeRequests() 定义哪些URL需要被保护、哪些不需要被保护
     * formLogin() 定义当需要用户登录时候，转到的登录页面
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    /**
     * 内存中创建了一个用户，该用户的名称为user，密码为password，用户角色为USER
     * 不加.passwordEncoder(new MyPasswordEncoder())，密码就不是以明文的方式进行匹配，会报错，java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
     * 加了.passwordEncoder(new MyPasswordEncoder())，密码就是以明文的方式进行匹配
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .passwordEncoder(new MyPasswordEncoder())
                .withUser("user")
                .password("password")
                .roles("USER");
    }
}
