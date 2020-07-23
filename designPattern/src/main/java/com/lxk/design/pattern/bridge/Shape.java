package com.lxk.design.pattern.bridge;

/**
 * @author LiXuekai on 2020/7/23
 */
public abstract class Shape {
    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}

