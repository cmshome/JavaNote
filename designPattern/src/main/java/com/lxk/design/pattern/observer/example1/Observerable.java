package com.lxk.design.pattern.observer.example1;


/**
 * 定义一个抽象被观察者接口
 * <p>
 * 抽象被观察者接口
 * 声明了添加、删除、通知观察者方法
 *
 * @author lxk on 2018/4/19
 */
public interface Observerable {

    /**
     * 添加
     *
     * @param o 观察者
     */
    void registerObserver(Observer o);

    /**
     * 删除
     *
     * @param o 观察者
     */
    void removeObserver(Observer o);

    /**
     * 通知
     */
    void notifyObserver();

}
