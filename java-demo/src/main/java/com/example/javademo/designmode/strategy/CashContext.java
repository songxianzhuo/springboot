package com.example.javademo.designmode.strategy;

import com.example.javademo.designmode.simpleFactory.OperationAdd;
import com.example.javademo.designmode.simpleFactory.OperationDiv;
import com.example.javademo.designmode.simpleFactory.OperationMul;
import com.example.javademo.designmode.simpleFactory.OperationSub;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/05
 **/
public class CashContext {

    private CashSuper cashAlgorithm;

    /**
     * 单一的策略模式，收银算法的选择交给调用方
     * @param cashAlgorithm
     */
    public CashContext(CashSuper cashAlgorithm) {
        this.cashAlgorithm = cashAlgorithm;
    }

    /**
     * 策略模式和简单工厂模式相结合，将算法的选择也交给上下文完成
     * 这样可以使调用方和算法代码彻底解耦
     * @param cashType
     */
    public CashContext(String cashType){
        switch (cashType) {
            case "正常消费":
                cashAlgorithm = new CashNormal();
                break;
            case "打8折":
                cashAlgorithm = new CashRebate(0.8);
                break;
            case "满300返50":
                cashAlgorithm = new CashReturn(300,50);
                break;
            default:
                break;
        }
    }

    /**
     * 间接调用各种算法，让算法和调用方解耦
     * @param money
     * @return
     */
    public double getResult(double money){
        return cashAlgorithm.acceptCash(money);
    }
}
