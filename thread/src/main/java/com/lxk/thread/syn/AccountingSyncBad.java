package com.lxk.thread.syn;

/**
 * @author LiXuekai on 2020/4/29
 */
public class AccountingSyncBad implements Runnable {
    static int i = 0;

    public synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //new新实例
        Thread t1 = new Thread(new AccountingSyncBad());
        //new新实例
        Thread t2 = new Thread(new AccountingSyncBad());
        t1.start();
        t2.start();

        // join 的作用就是让main线程稍息， t1 t2执行完之后 main 再继续
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
