package com.lxk.thread.bank;

/**
 * 银行存钱的多线程实例
 * <p>
 * 【需求：】
 * 银行有一个金库。
 * 有两个储户分别存300元，每次存100，存3次。
 * 目的：该程序是否有安全问题，如果有，如何解决？
 * <p>
 * 【如何找问题：】
 * 1，明确哪些代码是多线程运行代码。
 * 2，明确共享数据。
 * 3，明确多线程运行代码中哪些语句是操作共享数据的。
 *
 * @author lxk on 2017/6/26
 */
public class Main {
    public static void main(String[] args) {
        //一个银行and多个客户
        Bank bank = new Bank();
        int time = 10000;
        int money = 100;
        //这个客户存钱
        Customer c1 = new Customer(bank, Customer.TYPE_ADD, time, money);
        //这个客户取钱
        Customer c2 = new Customer(bank, Customer.TYPE_REDUCE, time, money);

        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        t1.start();
        t2.start();
    }
}
