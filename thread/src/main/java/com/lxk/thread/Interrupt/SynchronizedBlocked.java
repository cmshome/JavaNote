package com.lxk.thread.Interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author LiXuekai on 2020/4/30
 */
public class SynchronizedBlocked implements Runnable {

    public synchronized void f() {
        System.out.println("Trying to call f()");
        // Never releases lock
        while (true)
            Thread.yield();
    }

    /**
     * 在构造器中创建新线程并启动获取对象锁
     */
    public SynchronizedBlocked() {
        //该线程已持有当前实例锁
        Thread thread = new Thread() {
            public void run() {
                // Lock acquired by this thread
                f();
            }
        };
        thread.setName("constructor");
        thread.start();
    }

    public void run() {
        //中断判断
        while (true) {
            if (Thread.interrupted()) {
                System.out.println("中断线程!!");
                break;
            } else {
                f();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        SynchronizedBlocked sync = new SynchronizedBlocked();
        Thread t = new Thread(sync);
        t.setName("new in main");
        //启动后调用f()方法,无法获取当前实例锁处于等待状态 blocked
        t.start();
        TimeUnit.SECONDS.sleep(1);
        //中断线程,无法生效
        t.interrupt();
    }
    // 在SynchronizedBlocked构造函数中创建一个新线程并启动获取调用f()获取到当前实例锁，由于SynchronizedBlocked自身也是线程，
    // 启动后在其run方法中也调用了f()，
    // 但由于对象锁被其他线程占用，导致t线程只能等到锁，此时我们调用了t.interrupt();但并不能中断线程。

        /*
         * 输出结果:
         Trying to call f()
         */
}
