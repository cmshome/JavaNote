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
