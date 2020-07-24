package com.lxk.design.pattern.decorator.example0;

/**
 * 圆形
 *
 * @author LiXuekai on 2020/7/24
 */
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}
