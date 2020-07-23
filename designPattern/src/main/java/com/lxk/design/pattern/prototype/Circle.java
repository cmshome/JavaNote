package com.lxk.design.pattern.prototype;

/**
 * @author LiXuekai on 2020/7/23
 */
public class Circle extends Shape {

    public Circle() {
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
