package com.examplep.projectdemo.entity.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 持久化对象
 * 常用于多表联查和关联更新
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2019/12/26
 **/
@Data
@NoArgsConstructor
public class TestDO implements Serializable {
    private static final long serialVersionUID = -6581125189717252376L;
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
    /**
     * 用户对象
     */
    private TestPO testPO;
}
