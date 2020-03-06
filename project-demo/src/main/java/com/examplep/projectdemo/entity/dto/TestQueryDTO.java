package com.examplep.projectdemo.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2019/12/26
 **/
@Data
@NoArgsConstructor
public class TestQueryDTO {

    /**
     * 姓名
     */
    private String name;
    /**
     * 页数
     */
    private Integer pageNum;
    /**
     * 页面条数
     */
    private Integer pageSize;
}
