package com.lxk.jdk.clazz.init;

/**
 * 子类
 * <p>
 * @author lxk on 2017/4/20
 */
public class Child extends Parent {

    private String stringInChild = initStringInChild();
    private static String staticStringInChild = initStaticStringInChild();

    {
        System.out.println("9 子类：构造代码块");
    }

    static {
        System.out.println("4 子类：静态代码块");
    }

    public Child() {
        System.out.println("10 子类：构造方法");
    }

    private static String initStaticStringInChild() {
        System.out.println("3 子类：静态方法，被静态成员变量赋值调用。");
        return "initStaticStringInChild";
    }

    private String initStringInChild() {
        System.out.println("8 子类：普通成员方法，被普通成员变量赋值调用。");
        return "initStringInChild";
    }
}
