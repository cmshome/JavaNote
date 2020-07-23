package com.lxk.design.pattern.factory.abs;

/**
 * @author LiXuekai on 2020/7/23
 */
public class Blue implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }
}
