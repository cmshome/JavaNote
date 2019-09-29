package com.lxk.thread.ticket.extend;

/**
 * 卖票例子（继承Thread类，实现多线程）
 * <p>
 * @author lxk on 2017/6/25
 */
public class Main {
    public static void main(String[] args) {
        Ticket ticket1 = new Ticket("ticket1");
        Ticket ticket2 = new Ticket("ticket2");
        Ticket ticket3 = new Ticket("ticket3");
        Ticket ticket4 = new Ticket("ticket4");
        ticket1.start();
        ticket2.start();
        ticket3.start();
        ticket4.start();
    }
}
