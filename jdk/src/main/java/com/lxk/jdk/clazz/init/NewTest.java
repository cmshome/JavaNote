package com.lxk.jdk.clazz.init;

/**
 * 即是main方法是空白的，静态代码块还是会执行，还是会有输出。
 *
 * @author lxk on 2017/12/29
 */
public class NewTest {
    static {
        System.out.println("静态代码块1");
    }

    public static void main(String[] args) {

    }

    static {
        System.out.println("静态代码块2");
    }
}
