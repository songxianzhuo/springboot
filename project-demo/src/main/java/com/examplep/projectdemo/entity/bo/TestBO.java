package com.examplep.projectdemo.entity.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务层对象
 * 业务层最终生成对象
 * 可以充当VO
 *
 * @author Song Xianzhuo
 * @since 2019/12/26
 * @version 1.0.0
 **/
@Data
@NoArgsConstructor
public class TestBO {

    /**
     * id
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;


}
