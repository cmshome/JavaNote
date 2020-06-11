package com.lxk.design.pattern.singleton;

/**
 * 编译器优化，会有重排序的可能，导致bug的情况。
 *
 * @author LiXuekai on 2020/6/10
 */
public class SingletonPattern6 {
    private static SingletonPattern6 singletonInstance;

    private SingletonPattern6() {
    }

    public static SingletonPattern6 getSingletonInstance() {
        if (singletonInstance == null) {
            synchronized (SingletonPattern6.class) {
                singletonInstance = new SingletonPattern6();
            }
        }
        return singletonInstance;
    }
}
