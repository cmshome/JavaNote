package com.lxk.design.pattern.factory.ezsy;

/**
 * 圆形
 *
 * @author LiXuekai on 2020/7/23
 */
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}