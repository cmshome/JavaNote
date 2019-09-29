package com.lxk.thread.ticket.implement;

/**
 * 卖票例子（实现Runnable接口，实现多线程）
 * <p>
 * @author lxk on 2017/6/25
 */
public class Main {
    public static void main(String[] args) {
        //test();
        testStatic();
    }

    /**
     * 测试普通的同步代码块和同步函数的同步效果。
     * 结论：同步函数和同步代码块使用的锁都是this
     */
    private static void test() {
        Ticket t = new Ticket();

        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        //现在修改为只有2个线程，1使用同步代码块，2使用同步函数。
        //测试发现：两者使用的锁是不同的，因为使用的不是同一个锁，所以，线程还是不安全的。（下面分析为什么这要sleep()）
        t1.start();
        //这时候，在没有添加下面的sleep的时候，代码一运行，所有执行结果是：全走的是同步函数，
        //因为线程1启动完之后，瞬间，主线程已经把flag置成false啦，所以，2个线程都走的是false结果。
        //所以，要在这1线程启动完之后，主线程休息一下，就剩1线程在跑，才能看到2个线程分别的效果。
        try {
            Thread.sleep(10);
        } catch (Exception ignore) {
        }
        t.flag = false;
        t2.start();

        //运行结果：打印出0的错票。不安全。（此时，同步代码块使用的锁，是自己new的一个obj）
        //对错误代码进行分析如下：
        //两个前提。1，两个或以上的线程；2，用的是否是同一个锁。
        // 后面修改同步代码块中的同步对象由object变成this，然后就安全啦。
        //
        //这个修改完之后，就可以看到，没有输出0啦，而且2个线程，确实都执行了不同的同步实现。一个同步代码块，一个同步函数。


        //Thread t3 = new Thread(t);
        //Thread t4 = new Thread(t);
        //t3.start();
        //t4.start();

    }

    private static void testStatic() {
        TicketStatic t = new TicketStatic();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        //这时候，没有添加下面的sleep的时候，代码一运行，所有执行结果全走的是同步函数，
        //因为线程1启动完之后，瞬间，主线程已经把flag置成false啦，所以，都走的是false结果。
        //所以，要在这1线程启动完之后，主线程休息一下才能看到2个线程分别的效果。
        try {
            Thread.sleep(10);
        } catch (Exception ignore) {
        }
        t.flag = false;
        t2.start();

        //运行结果：打印出0的错票。不安全。（这个时候，同步代码块使用的锁，是自己this）
        //静态方法使用的锁和同步代码块使用的锁不一样。静态同步函数使用的锁是类.class
        //对错误代码进行分析如下：
        //两个前提。1，两个或以上的线程；2，用的是否是同一个锁。
        // 后面修改同步函数中的同步对象由this变成.class，然后就安全啦。
        //
        //这个修改完之后，就可以看到，没有输出0啦，而且2个线程，确实都执行了不同的同步实现。一个同步代码块，一个同步函数。

    }

}
