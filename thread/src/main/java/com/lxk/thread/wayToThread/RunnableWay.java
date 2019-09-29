package com.lxk.thread.wayToThread;

/**
 * @author lxk on 2017/6/25
 * 创建线程方式1实现
 * 实现Runnable接口，重写run方法
 */
public class RunnableWay implements Runnable {
    @Override
    public void run() {
        for (; ; ) {
            try {
                Thread.sleep(1000);//毫秒
                System.out.println(Thread.currentThread().getName() + "...implements Runnable way");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}