package com.example.javademo.designmode.simpleFactory;

/**
 * 描述 简单工厂模式，又称静态工厂模式
 * 创建一个工厂类，它可以根据参数的不同创建不同类的实例，被创建的实例通常都有共同的父类
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/07/05
 **/
public class OperationFactory {

    /**
     * 简单工厂模式中用于创建实例的方法应该是静态方法
     * @param operationType
     * @return
     */
    public static Operation createOperation(String operationType){
        Operation operation = null;
        switch (operationType) {
            case "add":
                operation = new OperationAdd();
                break;
            case "sub":
                operation = new OperationSub();
                break;
            case "mul":
                operation = new OperationMul();
                break;
            case "div":
                operation = new OperationDiv();
                break;
            default:
                break;
        }
        return operation;
    }


    public static void main(String[] args) {
        Operation add = OperationFactory.createOperation("add");
        System.out.println(add.operation(10,02));
    }
}
