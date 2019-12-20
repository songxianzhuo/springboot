package com.example.layerdesign.entity.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @ClassName: User
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/12/16 18:47
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class User {
    private Integer id;
    private String userName;
    private String password;
    private Integer sex;
    private String phone;
    private Integer age;
    private String nickName;
    private String address;
    private Integer status;
    private Timestamp createTime;
    private Timestamp updateTime;
}
