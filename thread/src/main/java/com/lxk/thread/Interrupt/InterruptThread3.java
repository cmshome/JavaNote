package com.lxk.thread.Interrupt;

import java.util.concurrent.TimeUnit;

/**
 * 当线程处于运行状态时，我们也可调用实例方法interrupt()进行线程中断，
 * 但同时必须手动判断中断状态，并编写中断线程的代码(其实就是结束run方法体的代码)
 * @author LiXuekai on 2020/4/30
 */
public class InterruptThread3 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    //判断当前线程是否被中断
                    if (this.isInterrupted()) {
                        System.out.println("线程中断");
                        break;
                    }
                }

                System.out.println("已跳出循环,线程中断!");
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t1.interrupt();

        /*
         * 输出结果:
         线程中断
         已跳出循环,线程中断!
         */
    }
}
