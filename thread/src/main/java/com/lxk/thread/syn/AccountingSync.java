package com.lxk.thread.syn;

/**
 * synchronized作用于实例方法
 * 修饰实例方法，作用于当前实例加锁，进入同步代码前要获得当前实例的锁
 * <p>
 * 修饰静态方法，作用于当前类对象加锁，进入同步代码前要获得当前类对象的锁
 * <p>
 * 修饰代码块，指定加锁对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁。
 * <p>
 * 所谓的实例对象锁就是用synchronized修饰实例对象中的实例方法，注意是实例方法不包括静态方法
 *
 * @author LiXuekai on 2020/4/29
 */
public class AccountingSync implements Runnable {
    /**
     * 共享资源(临界资源)
     */
    static int i = 0;

    /**
     * synchronized 修饰实例方法
     */
    public synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            increase();
        }
    }

    /**
     * 输出结果:
     * 2000000
     */
    public static void main(String[] args) throws InterruptedException {
        AccountingSync instance = new AccountingSync();
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
