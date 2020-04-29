package com.lxk.thread.join.case1;

/**
 * Thread类中的join()方法
 * <p>
 * 在一个线程中启动另外一个线程的join方法，当前线程将会挂起，而执行被启动的线程，
 * 直到被启动的线程执行完毕后，当前线程才开始执行。
 *
 * @author lxk on 2018/4/8
 */
public class Main {
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1("Thread 1");
        Thread2 thread2 = new Thread2("Thread 2", thread1);
        thread2.start();
    }
}

class Thread1 extends Thread {
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
        System.out.println(getName() + " is running over");

    }
}

class Thread2 extends Thread {
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
