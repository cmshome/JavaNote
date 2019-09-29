package com.lxk.thread.ticket.implement;

/**
 * 测试：静态同步函数和非静态的差别
 * <p>
 * @author lxk on 2017/6/25
 */
public class TicketStatic implements Runnable {
    private static int tick = 100;
    boolean flag = true;

    @Override
    public void run() {
        if (flag) {
            while (true) {
                //synchronized (this) {//静态同步函数使用的是类对象。
                synchronized (TicketStatic.class) {//内存中没有本类对象，但是一定有该类对应的字节码文件对象。类名.class
                    if (tick > 0) {
                        try {
                            Thread.sleep(10);
                        } catch (Exception ignore) {
                        }
                        System.out.println(Thread.currentThread().getName() + "....sale...代码块 : " + tick--);
                    }
                }
            }
        } else {
            while (true) {
                show();
            }
        }
    }

    private static synchronized void show() {//静态方法的时候，使用的锁就不是this，经测试，可发现使用的是类.class
        if (tick > 0) {
            try {
                Thread.sleep(10);
            } catch (Exception ignore) {
            }
            System.out.println(Thread.currentThread().getName() + "....sale...函数 : " + tick--);
        }
    }
}
