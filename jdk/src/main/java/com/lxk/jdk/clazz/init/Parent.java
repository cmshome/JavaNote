package com.lxk.jdk.clazz.init;

/**
 * 父类
 * <p>
 * @author lxk on 2017/4/20
 */
public class Parent {
    static {
        System.out.println("1 父类：静态代码块");
    }

    {
        System.out.println("5 父类：构造代码块");
    }

    private static String staticStringInParent = initStaticStringInParent();

    private String stringInParent = initStringInParent();

    public Parent() {
        System.out.println("7 父类：构造方法");
    }

    private static String initStaticStringInParent() {
        System.out.println("2 父类：静态方法，被静态成员变量赋值调用。");
        return "initStaticStringInParent";
    }
    private String initStringInParent() {
        System.out.println("6 父类：普通成员方法，被普通成员变量赋值调用。");
        return "initStringInParent";
    }

}
