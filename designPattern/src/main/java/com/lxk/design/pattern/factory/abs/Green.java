package com.lxk.design.pattern.factory.abs;

/**
 * @author LiXuekai on 2020/7/23
 */
public class Green implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}
