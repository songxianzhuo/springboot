package com.examplep.projectdemo.entity.feo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 数据查询对象
 * 超过2个参数的查询建议封装
 * 贯穿始终，直至数据库查询，一般用于分页查询，可以进行必要校验
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2019/12/26
 **/
@Data
@NoArgsConstructor
public class TestQueryFEO {

    /**
     * 姓名
     */
    private String name;
    /**
     * 页数
     */
    @NotNull
    private Integer pageNum;
    /**
     * 页面条数
     */
    @NotNull
    private Integer pageSize;
}
