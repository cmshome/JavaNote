package com.lxk.thread.ticket.extend;

/**
 * @author lxk on 2017/6/25
 */
public class Ticket extends Thread {
    //private int ticket = 100;//创建一个对象就有100张票。错误一：几个线程都打印一次100-1。不合适。所以，如下操作，换成静态变量。
    private static int ticket = 100;//静态变量是所有对象都共享，100张票。几个线程，卖的都是一个票。但是，一般都不这么干，静态变量，生命周期太长。

    /**
     * 实现自定义线程的名称
     */
    public Ticket(String name) {
        super(name);
    }

    /**
     * 这地方就是需要注意的地方，如果不加[synchronized]，就会发生线程安全问题。
     * 奇怪了，
     * 怎么还是线程不安全，还是会执行出0，-1，-2。的结果出来。
     * 错误原因的分析：
     * 可以看到添加的锁的对象是this，但是在main方法中有4个对象，每个对象都对自己加锁，锁不同，所以，还是不安全的。
     * 比如：换成都对Ticket.class（内存中就有一份字节码文件存在）加锁，那就安全啦。
     */
    @Override
    //public synchronized void run() {//【①】 错误二：即使添加了同步方法(此处注释的代码)，锁的是this，是线程不安全的。
    public void run() {
        while (true) {
            //synchronized (this) {//【①】 错误二：即使添加了同步代码块，锁的也是this，是线程不安全的。
            //synchronized (Ticket.class) {//【①】正确同步方式： 必须所有实例化的对象都锁相同的一个家伙，那就安全啦。
                if (ticket > 0) {
                    //睡一下，好实现线程不安全的现象,前提是这个方法，没有添加synchronized，同步函数。
                    try {
                        Thread.sleep(10);
                    } catch (Exception ignored) {
                    }
                    //错误三：不添加同步(即注释掉标记:①的所有代码)，多线程操作则会打印出0，-1，-2
                    //分析：
                    //假设1线程运行到下行代码处，还未执行，此时ticket的值仍然为1，那么其他线程继续执行还是都会进到这个判断
                    //假设其他几个线程都恰好停到此处，那么依次执行完之后，四个线程的结果就是，1，0，-1，-2.
                    System.out.println(this.getName() + " sale：" + ticket--);
                }
            //} //【①】
        }
    }
}
