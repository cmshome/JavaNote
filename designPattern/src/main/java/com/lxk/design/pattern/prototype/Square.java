package com.lxk.design.pattern.prototype;

/**
 * @author LiXuekai on 2020/7/23
 */
public class Square extends Shape {

    public Square() {
        type = "Square";
    }

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
