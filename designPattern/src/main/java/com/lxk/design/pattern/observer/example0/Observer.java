package com.lxk.design.pattern.observer.example0;

/**
 * @author LiXuekai on 2020/7/24
 */
public abstract class Observer {
    protected Subject subject;

    public abstract void update();
}
