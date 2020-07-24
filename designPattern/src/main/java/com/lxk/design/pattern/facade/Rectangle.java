package com.lxk.design.pattern.facade;

/**
 * @author LiXuekai on 2020/7/24
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Rectangle::draw()");
    }
}
