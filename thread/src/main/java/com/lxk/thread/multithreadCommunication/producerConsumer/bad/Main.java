package com.lxk.thread.multithreadCommunication.producerConsumer.bad;

/**
 * 多个生产者和多个消费者的多线程例子
 * <p>
 * @author lxk on 2017/6/27
 */
public class Main {
    public static void main(String[] args) {
        Resource r = new Resource();

        Producer pro = new Producer(r);
        Consumer con = new Consumer(r);

        Thread t1 = new Thread(pro);
        Thread t2 = new Thread(pro);
        Thread t3 = new Thread(con);
        Thread t5 = new Thread(pro);
        Thread t6 = new Thread(con);
        Thread t4 = new Thread(con);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

    }
}
