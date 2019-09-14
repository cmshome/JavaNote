package com.lxk.design.pattern.proxy.statics;

public class MainTest {
    public static void main(String[] args) {
        //横向业务流：方法1();
        //。。。
        TargetInterface proxy = new ProxyObject(new TargetObject());
        proxy.business();//代理对象来代理目标对象，执行目标方法。
        //。。。
        //横向业务流：方法7();
    }
}
