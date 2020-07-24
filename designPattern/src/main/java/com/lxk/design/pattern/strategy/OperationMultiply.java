package com.lxk.design.pattern.strategy;

/**
 * 乘
 *
 * @author LiXuekai on 2020/7/24
 */
public class OperationMultiply implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}
