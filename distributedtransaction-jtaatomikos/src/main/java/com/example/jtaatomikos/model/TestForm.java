package com.example.jtaatomikos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @ClassName: TestForm
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/11/18 15:51
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class TestForm {
    private Province province;
    private City city;
    private BigDecimal result;
}
