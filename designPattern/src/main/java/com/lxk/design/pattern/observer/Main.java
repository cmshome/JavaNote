package com.lxk.design.pattern.observer;

/**
 * 观察者模式
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
