package com.lxk.thread.mianShiTest.one;

/**
 * @author lxk on 2017/7/14
 */
public class Child implements Runnable {
    private final Object object;

    public Child(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 50) {
            synchronized (object) {
                System.out.println("第" + (i + 1) + "...子50");
                i++;
                object.notify();
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
