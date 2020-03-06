package com.example.swaggerdemo.model.vo;

import lombok.Data;

/**
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/12/25
 **/
@Data
public class CityVO {
    /**
     * 城市id
     */
    private Integer id;
    /**
     * 城市名称
     */
    private String name;
    /**
     * 城市简介
     */
    private String desc;
}
