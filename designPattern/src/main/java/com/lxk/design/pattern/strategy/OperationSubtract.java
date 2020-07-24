package com.lxk.design.pattern.strategy;

/**
 * Subtract
 * vt. 减去；扣掉
 *
 * @author LiXuekai on 2020/7/24
 */
public class OperationSubtract implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}
