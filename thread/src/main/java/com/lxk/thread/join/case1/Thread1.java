package com.lxk.thread.join.case1;

/**
 * @author lxk on 2018/4/8
 */
public class Thread1 extends Thread {
    public Thread1(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        System.out.println(getName() + " is running");
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
