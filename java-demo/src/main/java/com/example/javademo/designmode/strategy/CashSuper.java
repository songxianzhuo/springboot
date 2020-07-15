package com.example.javademo.designmode.strategy;

/**
 * 描述 场景是：超市收银，有多种打折和促销活动
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/05
 **/
public abstract class CashSuper {

    /**
     * 定义收银的抽象方法
     * @param money
     * @return
     */
    public abstract double acceptCash(double money);
}
