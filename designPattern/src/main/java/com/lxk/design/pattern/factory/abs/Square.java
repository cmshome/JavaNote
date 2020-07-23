package com.lxk.design.pattern.factory.abs;

/**
 * @author LiXuekai on 2020/7/23
 */
public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
