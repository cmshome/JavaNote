package com.lxk.thread.syn;

/**
 * synchronized同步代码块
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
