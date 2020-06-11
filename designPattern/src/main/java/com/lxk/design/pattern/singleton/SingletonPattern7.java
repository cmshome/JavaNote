package com.lxk.design.pattern.singleton;

/**
 * 枚举类型的单例模式
 * 对于枚举，JVM 会自动进行实例的创建，其构造方法由 JVM 在创建实例的时候进行调用
 * 我们在代码中是获取不到 enum 类的构造方法的
 *
 * @author LiXuekai on 2020/6/11
 */
public enum SingletonPattern7 {
    SINGLETON_PATTERN_7;

    public static SingletonPattern7 getSingletonInstance() {
        return SINGLETON_PATTERN_7;
    }
}
