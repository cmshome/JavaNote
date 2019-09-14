package com.lxk.design.pattern.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理-拦截器
 * <p>
 * @author lxk on 2016/11/25
 */
public class MyInterceptor implements InvocationHandler {
    /**
     * 目标类
     */
    private Object target;

    public MyInterceptor(Object target) {
        this.target = target;
    }

    /**
     * args 目标方法的参数
     * method 目标方法
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //切面方法a();
        System.out.println("aaaaa");
        //。。。
        //调用目标类的目标方法
        method.invoke(this.target, args);
        //。。。
        //切面方法f();
        System.out.println("bbbbb");
        return null;
    }
}
