package com.lxk.thread.mianShiTest.staticAttribute;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lxk on 2017/11/17
 */
public class MyThread implements Runnable {

    private static AtomicInteger i = new AtomicInteger(0);

    @Override
    public void run() {
        while (true) {
            synchronized (i) {
                if (i.get() < 100) {
                    //相当于i++
                    i.getAndIncrement();
                    String currentThreadName = Thread.currentThread().getName();
                    System.out.println(currentThreadName + " i = " + i);
                } else {
                    break;
                }
            }
        }

    }

}
