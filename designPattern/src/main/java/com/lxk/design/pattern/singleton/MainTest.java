package com.lxk.design.pattern.singleton;

import org.junit.Test;

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
public class MainTest {

    @Test
    public void main() {
        goodWay();
        badWay();
    }

    private void badWay() {
        // 对，但不好
        SingletonPattern1 singletonPattern1 = SingletonPattern1.getSingletonInstance();
        // 错
        SingletonPattern2 singletonPattern2 = SingletonPattern2.getSingletonInstance();
        // 对，但不好
        SingletonPattern5 singletonPattern5 = SingletonPattern5.getSingletonInstance();
        // 错
        SingletonPattern6 singletonPattern6 = SingletonPattern6.getSingletonInstance();
    }

    private void goodWay() {
        // 多线程互斥通信保证单例模式
        SingletonPattern3 singletonPattern3 = SingletonPattern3.getSingletonInstance();
        // 类加载机制保证单例
        SingletonPattern4 singletonPattern4 = SingletonPattern4.getSingletonInstance();
    }
}
