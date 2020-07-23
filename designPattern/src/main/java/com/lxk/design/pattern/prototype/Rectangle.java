package com.lxk.design.pattern.prototype;

/**
 * @author LiXuekai on 2020/7/23
 */
public class Rectangle extends Shape {

    public Rectangle() {
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
