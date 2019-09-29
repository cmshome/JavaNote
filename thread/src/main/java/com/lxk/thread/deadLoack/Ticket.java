package com.lxk.thread.deadLoack;

/**
 * @author lxk on 2017/6/25
 */
public class Ticket implements Runnable {
    private int tick = 1000;
    boolean flag = true;
    private final Object object = new Object();

    @Override
    public void run() {
        if (flag) {
            while (true) {
                synchronized (object) {
                    show();
                }
            }
        } else {
            while (true) {
                show();
            }
        }
    }

    private synchronized void show() {//使用的锁就是this
        synchronized (object) {
            if (tick > 0) {
                try {
                    Thread.sleep(10);
                } catch (Exception ignore) {
                }
                System.out.println(Thread.currentThread().getName() + "....sale....代码块 : " + tick--);
            }
        }
    }
}
