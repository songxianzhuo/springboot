package com.example.layerdesign.entity.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @ClassName: Order
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/12/16 19:43
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class Order {
    private Integer id;
    private String orderNo;
    private Timestamp orderTime;
    private BigDecimal totalMoney;
    private Integer point;
    private Integer userId;
    private Timestamp createTime;
    private Timestamp updateTime;
}
