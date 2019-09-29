package com.lxk.thread.wayToThread;

/**
 * @author lxk on 2017/6/25
 * 创建线程方式2实现
 * 继承Thread类，重写run函数
 */
public class ExtendWay extends Thread {
    @Override
    public void run() {
        for (; ; ) {
            try {
                Thread.sleep(1000);//毫秒
                System.out.println(Thread.currentThread().getName() + "...extends way");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}