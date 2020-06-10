package com.lxk.design.pattern.singleton;

/**
 * 饿汉式
 * 优点：没有加锁，执行效率会提高。
 * 缺点：类加载时就初始化，浪费内存。
 *
 * @author lxk on 2017/3/23
 */
public class SingletonPattern1 {
    //有的会加final修饰符（更为严谨），添加final修饰符之后，指向的引用不能再做更改。
    //这是final的用法：final成员变量表示常量，只能被赋值一次，赋值后值不能再改变。
    //这句话得这么理解：
    //对于一个final变量。
    // 如果是基本数据类型的变量，则其数值一旦在初始化之后便不能更改；
    // 如果是引用类型的变量，则在对其初始化之后便不能再让其指向另一个对象。
    private static final SingletonPattern1 singletonInstance = new SingletonPattern1();

    private SingletonPattern1() {
    }

    public static SingletonPattern1 getSingletonInstance() {
        return singletonInstance;
    }
}