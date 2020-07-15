package com.example.javademo.designmode.strategy;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/05
 **/
public class CashTest {

    public static void main(String[] args) {
        System.out.println("收银");
        System.out.println("商品：羽绒服，单价：360，数量：1");
        System.out.println("参与活动：满300返50");
        CashContext cashContext = new CashContext("满300返50");
        System.out.println("最后金额：" + cashContext.getResult(400 * 1));
    }
}
