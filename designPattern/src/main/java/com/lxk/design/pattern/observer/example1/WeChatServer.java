package com.lxk.design.pattern.observer.example1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxk on 2018/4/19
 */
public class WeChatServer implements Observerable {

    /**
     * 注意到这个List集合的泛型参数为Observer接口，
     * 设计原则：面向接口编程而不是面向实现编程
     */
    private List<Observer> list;
    private String message;

    public WeChatServer() {
        list = new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer o) {
        list.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if (!list.isEmpty()) {
            list.remove(o);
        }
    }


    @Override
    public void notifyObserver() {
        for (Observer observer : list) {
            observer.update(message);
        }
    }

    public void setInformation(String s) {
        this.message = s;
        System.out.println("微信服务更新消息： " + s);
        //消息更新，通知所有观察者
        notifyObserver();
    }

}
