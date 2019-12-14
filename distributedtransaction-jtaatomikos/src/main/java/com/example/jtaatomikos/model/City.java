package com.example.jtaatomikos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @ClassName: City
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/11/18 15:31
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class City {
    private Integer id;
    private String name;
    private BigDecimal number;
}
