package com.lxk.design.pattern.decorator.example0;

/**
 * 矩形
 *
 * @author LiXuekai on 2020/7/24
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}
