package com.lxk.design.pattern.observer.example1;

/**
 * 观察者模式
 * 当对象间存在一对多关系时，则使用观察者模式（Observer Pattern）
 * 一个对象状态改变给其他对象通知的问题，而且要考虑到易用和低耦合，保证高度的协作。
 * 关键代码：在抽象类里有一个 ArrayList 存放观察者们。
 *
 * @author lxk on 2018/4/19
 */
public class Main {
    public static void main(String[] args) {
        WeChatServer server = new WeChatServer();

        Observer userZhang = new User("ZhangSan");
        Observer userLi = new User("LiSi");
        Observer userWang = new User("WangWu");

        server.registerObserver(userZhang);
        server.registerObserver(userLi);
        server.registerObserver(userWang);
        server.setInformation("PHP是世界上最好用的语言！");

        System.out.println("----------------------------------------------");
        server.removeObserver(userZhang);
        server.setInformation("JAVA是世界上最好用的语言！");
    }
}
