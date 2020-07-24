package com.lxk.design.pattern.proxy.dynamic.jdk;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        //目标对象
        TargetObject target = new TargetObject();
        //拦截器
        MyInterceptor myInterceptor = new MyInterceptor(target);

        /*
         *  Proxy.newProxyInstance参数：
         * 	1、目标类的类加载器
         * 	2、目标类的所有的接口
         *  3、拦截器
         */
        //代理对象，调用系统方法自动生成
        TargetInterface proxyObj = (TargetInterface) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), myInterceptor);
        proxyObj.business();
    }
}
