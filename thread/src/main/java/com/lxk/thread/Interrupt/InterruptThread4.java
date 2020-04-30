package com.lxk.thread.Interrupt;

import java.util.concurrent.TimeUnit;

/**
 * 一种是当线程处于阻塞状态或者试图执行一个阻塞操作时，我们可以使用实例方法interrupt()进行线程中断，执行中断操作后将会抛出interruptException异常(该异常必须捕捉无法向外抛出)并将中断状态复位，
 * 另外一种是当线程处于运行状态时，我们也可调用实例方法interrupt()进行线程中断，但同时必须手动判断中断状态，并编写中断线程的代码(其实就是结束run方法体的代码)。
 * 有时我们在编码时可能需要兼顾以上两种情况，那么就可以如下编写
 *
 * @author LiXuekai on 2020/4/30
 */
public class InterruptThread4 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    //判断当前线程是否已中断,注意interrupted方法是静态的,执行后会对中断状态进行复位
                    while (!Thread.interrupted()) {
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t1.interrupt();

        /*
         * 输出结果:
         java.lang.InterruptedException: sleep interrupted
         */
    }
}
