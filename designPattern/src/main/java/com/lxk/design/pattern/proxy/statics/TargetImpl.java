package com.lxk.design.pattern.proxy.statics;

/**
 * 被代理对象
 * 目标对象类
 * 实现目标接口.
 * 继而实现目标方法。
 */
public class TargetImpl implements Target {

    /**
     * 目标方法(即目标操作)
     */
    @Override
    public void business() {
        System.out.println("business");
    }

}
