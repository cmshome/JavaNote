package com.lxk.thread.syn;

/**
 * synchronized同步代码块
 * 修饰实例方法，作用于当前实例加锁，进入同步代码前要获得当前实例的锁
 * <p>
 * 修饰静态方法，作用于当前类对象加锁，进入同步代码前要获得当前类对象的锁
 * <p>
 * 修饰代码块，指定加锁对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁。
 *
 * @author LiXuekai on 2020/4/29
 */
public class AccountingSyncCode implements Runnable {
    private static final AccountingSyncCode instance = new AccountingSyncCode();
    static int i = 0;

    @Override
    public void run() {
        //省略其他耗时操作....
        //使用同步代码块对变量i进行同步操作,锁对象为instance
        synchronized (instance) {
            for (int j = 0; j < 1000000; j++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
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
