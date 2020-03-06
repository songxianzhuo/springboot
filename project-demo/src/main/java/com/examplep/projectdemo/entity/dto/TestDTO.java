package com.examplep.projectdemo.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 控制层和业务层之间传参对象
 * 向上可以充当FEO
 * 向下可以充当PO，DO
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2019/12/26
 **/
@Data
@NoArgsConstructor
public class TestDTO {

    /**
     * id
     */
    private Integer id = -1;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
}
