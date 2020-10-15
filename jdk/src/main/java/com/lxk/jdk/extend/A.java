package com.lxk.jdk.extend;

/**
 * @author LiXuekai on 2020/10/15
 */
public class A {
    protected int method(int a, int b) {
        return 0;
    }
}

class B extends A {

    /**
     * 复写之后，权限不得小于父类
     */
    @Override
    public int method(int a, int b) {
        return 0;
    }
}
