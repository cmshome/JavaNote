package com.lxk.thread.mianShiTest.staticAttribute;

/**
 * 首先是2个线程一起执行，再有就是i++他不是原子操作。
 *
 * @author lxk on 2017/11/17
 */
public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread());
        Thread t2 = new Thread(new MyThread());
        t1.start();
        t2.start();
    }
}
