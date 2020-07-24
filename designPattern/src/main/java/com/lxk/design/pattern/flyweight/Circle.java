package com.lxk.design.pattern.flyweight;

import lombok.Data;

/**
 * @author LiXuekai on 2020/7/24
 */
@Data
public class Circle implements Shape {
    private String color;
    private int x;
    private int y;
    private int radius;

    public Circle(String color) {
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println("Circle: Draw() [Color : " + color + ", x : " + x + ", y :" + y + ", radius :" + radius);
    }
}
