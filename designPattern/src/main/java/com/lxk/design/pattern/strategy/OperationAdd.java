package com.lxk.design.pattern.strategy;

/**
 * +
 *
 * @author LiXuekai on 2020/7/24
 */
public class OperationAdd implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
