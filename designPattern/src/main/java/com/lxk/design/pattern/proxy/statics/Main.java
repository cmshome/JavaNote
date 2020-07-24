package com.lxk.design.pattern.proxy.statics;

/**
 * 代理模式（Proxy Pattern）中，一个类代表另一个类的功能。
 * 在代理模式中，我们创建具有现有对象的对象，以便向外界提供功能接口。
 *
 * @author lxk
 */
public class Main {
    public static void main(String[] args) {
        //横向业务流：方法1();
        //。。。
        Target proxy = new ProxyTarget(new TargetImpl());
        proxy.business();//代理对象来代理目标对象，执行目标方法。
        //。。。
        //横向业务流：方法7();
    }
}
