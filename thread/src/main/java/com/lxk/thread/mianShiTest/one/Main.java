package com.lxk.thread.mianShiTest.one;


/**
 * 子线程循环 10 次，接着主线程循环 100 次，接着又回到子线程循环 10 次，
 * 接着再回到主线程又循环 100 次，如此循环50次，试写出代码。
 * <p>
 * @author lxk on 2017/7/14
 */
public class Main {
    public static void main(String[] args) {
        int i = 0;
        Object o = new Object();
        new Thread(new Child(o)).start();
        while (i < 50) {
            synchronized (o) {
                try {
                    o.wait();
                } catch (InterruptedException ignore) {
                }
                System.out.println("第" + (i + 1) + "...主...100");
                i++;
                o.notify();
            }
        }
        System.out.println("结束");
    }

}
