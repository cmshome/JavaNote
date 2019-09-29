package com.lxk.jdk.throwable;

/**
 * @author lxk on 2018/4/16
 */
public class StackDeepTest {
    private int count = 0;

    public void testAdd() {
        count++;
        testAdd();
    }

    public void test() {
        try {
            testAdd();
        } catch (Throwable e) {
            System.out.println(e);
            System.out.println("栈深度:" + count);
        }
    }
}
