package com.lxk.bean.model;

/**
 * 为测试堆内存溢出，构造函数递归调用。
 *
 * @author lxk on 2018/3/15
 */
public class Test {
    byte[] data = new byte[10 * 1024 * 1024];
    public Test test = new Test();
}
