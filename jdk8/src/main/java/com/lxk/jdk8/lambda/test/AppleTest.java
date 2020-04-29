package com.lxk.jdk8.lambda.test;

/**
 * @author LiXuekai on 2020/4/29
 * 函数式接口 AppleTest、BiAppleTest分别对应着java8提供的Predicate、BiPredicate
 */
@FunctionalInterface
public interface AppleTest<T> {
    boolean test(T t);
}
