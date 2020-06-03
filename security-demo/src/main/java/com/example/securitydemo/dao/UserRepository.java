package com.example.securitydemo.dao;

import com.example.securitydemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/05/19
 **/
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);
}
