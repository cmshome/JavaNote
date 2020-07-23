package com.lxk.design.pattern.factory.ezsy;

/**
 * 正方形
 *
 * @author LiXuekai on 2020/7/23
 */
public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
