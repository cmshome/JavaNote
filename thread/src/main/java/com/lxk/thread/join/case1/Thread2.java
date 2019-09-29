package com.lxk.thread.join.case1;

/**
 * @author lxk on 2018/4/8
 */
public class Thread2 extends Thread {
    private Thread1 thread1;

    public Thread2(String threadName, Thread1 thread1) {
        super(threadName);
        this.thread1 = thread1;
    }

    @Override
    public void run() {
        System.out.println(getName() + " is running");
        try {
            thread1.start();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread2 is over");
    }
}
