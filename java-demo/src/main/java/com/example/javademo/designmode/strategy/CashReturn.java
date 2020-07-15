package com.example.javademo.designmode.strategy;

/**
 * 描述 返利
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/05
 **/
public class CashReturn extends CashSuper {

    private int condition;
    private int returnMoney;

    public CashReturn(int condition, int returnMoney) {
        this.condition = condition;
        this.returnMoney = returnMoney;
    }

    @Override
    public double acceptCash(double money) {
        if(money > condition){
            money-=(int)money/condition*returnMoney;
        }
        return money;
    }
}
