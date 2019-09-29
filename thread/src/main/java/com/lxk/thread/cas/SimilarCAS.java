package com.lxk.thread.cas;

/**
 * @author lxk on 2018/5/3
 */
public class SimilarCAS {
    private int value;

    public int getValue() {
        return value;
    }

    /**
     * 这里只能用synchronized了,毕竟无法调用操作系统的CAS
     */
    public synchronized boolean compareAndSwap(int expectedValue, int newValue) {
        if (value == expectedValue) {
            value = newValue;
            return true;
        }
        return false;
    }
}
