package com.lxk.thread.deadLoack;


/**
 * 多线程死锁的例子
 * 死锁。
 * 同步中嵌套同步。
 * <p>
 * @author lxk on 2017/6/26
 */
public class Main {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        Ticket t = new Ticket();

        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        System.out.println("---");
        t1.start();
        try {
            Thread.sleep(10);
        } catch (Exception ignore) {
        }
        t.flag = false;
        t2.start();

    }
}
