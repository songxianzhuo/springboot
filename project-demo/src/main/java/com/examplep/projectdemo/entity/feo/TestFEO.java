package com.examplep.projectdemo.entity.feo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 接受前端参数POJO
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2019/12/26
 **/
@Data
@NoArgsConstructor
public class TestFEO {

    /**
     * id
     */
    private Integer id = -1;
    /**
     * 姓名
     */
    @NotBlank(message = "姓名不许为空")
    private String name;
    /**
     * 年龄
     */
    @NotNull(message = "年龄不许为空")
    @Min(value = 1,message = "年龄必须在1和100之间")
    @Max(value = 100,message = "年龄必须在1和100之间")
    private Integer age;
}
