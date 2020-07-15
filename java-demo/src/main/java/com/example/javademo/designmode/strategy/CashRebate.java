package com.example.javademo.designmode.strategy;

/**
 * 描述 折扣
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/05
 **/
public class CashRebate extends CashSuper {

    private double discount = 1;

    public CashRebate(double discount) {
        this.discount = discount;
    }

    @Override
    public double acceptCash(double money) {
        return money * discount;
    }
}
