package com.lxk.thread.ticket.implement;

/**
 * @author lxk on 2017/6/25
 */
public class Ticket implements Runnable {
    private int tick = 100;
    boolean flag = true;
    //Object object = new Object();

    @Override
    public void run() {
        if (flag) {
            while (true) {
                //synchronized (object) {//这个同步代码块使用的锁是object，而下面的同步函数使用的是锁是this，所以，这么干就线程不安全。
                synchronized (this) {//换成this就变得安全啦。说明下面同步函数使用的锁是this
                    if (tick > 0) {
                        try {
                            Thread.sleep(10);
                        } catch (Exception ignore) {
                        }
                        System.out.println(Thread.currentThread().getName() + "....sale...代码块 : " + tick--);
                    }
                }
            }
        } else {
            while (true) {
                show();
            }
        }
    }

    private synchronized void show() {//使用的锁就是this
        if (tick > 0) {
            try {
                Thread.sleep(10);
            } catch (Exception ignore) {
            }
            System.out.println(Thread.currentThread().getName() + "....sale...函数 : " + tick--);
        }
    }
}
