package com.lxk.jdk.interfaces;

/**
 * 主要是测试一个接口当函数的参数
 *
 * @author LiXuekai on 2019/8/30
 */
public class Main {
    public static void main(String[] args) {
        Implement implement = new Implement();
        implement.loadSyncStream(System.out::println);
    }
}
