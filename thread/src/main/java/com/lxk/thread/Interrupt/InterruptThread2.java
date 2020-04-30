package com.lxk.thread.Interrupt;

import java.util.concurrent.TimeUnit;

/**
 * 处于运行期且非阻塞的状态的线程
 * 直接调用Thread.interrupt()中断线程是不会得到任响应的
 *
 * @author LiXuekai on 2020/4/30
 */
public class InterruptThread2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("未被中断");
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t1.interrupt();

        /*
         * 输出结果(无限执行):
         未被中断
         未被中断
         未被中断
         ......
         */

        // 虽然我们调用了interrupt方法，但线程t1并未被中断，因为处于非阻塞状态的线程需要我们手动进行中断检测并结束程序
    }
}
