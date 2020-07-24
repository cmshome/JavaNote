package com.lxk.design.pattern.observer.example1;

/**
 * 抽象观察者
 * 定义了一个update()方法，
 * 当被观察者调用notifyObservers()方法时，
 * 观察者的update()方法会被回调。
 *
 * @author lxk on 2018/4/19
 */
public interface Observer {
    /**
     * 更新
     *
     * @param message message
     */
    void update(String message);
}
