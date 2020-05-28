package com.lxk.thread.syn;

import java.util.concurrent.TimeUnit;

/**
 * 当一个线程正在访问一个对象的 synchronized 实例方法，那么其他线程不能访问该对象的其他 synchronized 方法
 *
 * @author LiXuekai on 2020/5/11
 */
public class AccountingSync2 implements Runnable {
    /**
     * 共享资源(临界资源)
     */
    static int i = 0;


    /**
     * synchronized 修饰实例方法
     */
    public synchronized void increase() {
        System.out.println("i % 2 == 0 current i is " + i + " increase is run , thread name is " + Thread.currentThread().getName());
        i++;
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("increase is sleep over , thread name is " + Thread.currentThread().getName());
    }

    public synchronized void increase2() {
        System.out.println("i % 2 == 1 current i is " + i);
        i++;
        System.out.println("increase2 is run , thread name is " + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        for (int j = 0; j < 3; j++) {
            if (i % 2 == 0) {
                System.out.println("increase() is run ... ");
                increase();
                System.out.println("increase() is run over ... ");
            } else {
                System.out.println("increase2() is run ... ");
                increase2();
                System.out.println("increase2() is run over ... ");
            }
            System.out.println();
        }
    }

    /**
     * 输出结果:
     * 2000000
     */
    public static void main(String[] args) throws InterruptedException {
        AccountingSync2 instance = new AccountingSync2();
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();

        // join 的作用就是让main线程稍息， t1 t2执行完之后 main 再继续
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
