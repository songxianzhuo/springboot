package com.example.layerdesign.entity.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @ClassName: OrderGoods
 * @Description: TODO
 * @Author: SONG
 * @Date: 2019/12/16 19:46
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class OrderGoods {
    private Integer id;
    private Integer orderId;
    private String name;
    private BigDecimal totalMoney;
    private Integer number;
    private Timestamp createTime;
    private Timestamp updateTime;
}
