package com.example.javademo.designmode.simpleFactory;

import org.springframework.util.Assert;

/**
 * 描述
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/05
 **/
public class OperationDiv extends Operation {

    @Override
    public Double operation(double numberA, double numberB) {
        Assert.isTrue(numberB == 0,"被除数不可以为0！");
        return numberA / numberB;
    }
}
