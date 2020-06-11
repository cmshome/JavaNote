package com.lxk.design.pattern.singleton;

import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * 单例模式
 * 意图：保证一个类仅有一个实例，并提供一个访问它的全局访问点。
 * 主要解决：一个全局使用的类频繁地创建与销毁。
 * 何时使用：当想控制实例数目，节省系统资源的时候。
 * 如何解决：判断系统是否已经有这个单例，如果有则返回，如果没有则创建。
 * 关键代码：构造函数是私有的。
 *
 * @author lxk on 2017/3/23
 */
public class Main {

    @Test
    public void main() throws Exception {
        wrongWay();
        badWay();
        goodWay();
        specialWay();
    }

    /**
     * 错误实现方式
     */
    private void wrongWay() {
        // 错，未考虑多线程的情况。
        SingletonPattern2 singletonPattern2 = SingletonPattern2.getSingletonInstance();

        // 错。虽然考虑多线程，且有双重旋锁来保证同步，但是jvm可能会重排序，导致异常。
        SingletonPattern6 singletonPattern6 = SingletonPattern6.getSingletonInstance();
    }

    /**
     * 效率不太好的方式
     */
    private void badWay() {
        // 对，但不好。类加载就会创建这个单例。
        SingletonPattern1 singletonPattern1 = SingletonPattern1.getSingletonInstance();

        // 对，但不好。同步方法只能互斥访问。
        SingletonPattern5 singletonPattern5 = SingletonPattern5.getSingletonInstance();
    }

    /**
     * 常见的方式
     */
    private void goodWay() {
        // 双旋锁 且 volatile
        SingletonPattern3 singletonPattern3 = SingletonPattern3.getSingletonInstance();

        // 类加载机制保证单例
        SingletonPattern4 singletonPattern4 = SingletonPattern4.getSingletonInstance();

        // 对于枚举，JVM 会自动进行实例的创建，反射拿不到构造函数的。
        SingletonPattern7 singletonPattern7 = SingletonPattern7.getSingletonInstance();

    }

    /**
     * 通过反射来再次初始化单例，使得单例不再单例。
     */
    private void specialWay() throws Exception {
        SingletonPattern3 singletonPattern3 = SingletonPattern3.getSingletonInstance();
        Constructor<SingletonPattern3> constructor = SingletonPattern3.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        SingletonPattern3 reflect3 = constructor.newInstance();
        // false
        System.out.println(singletonPattern3 == reflect3);
    }

}
