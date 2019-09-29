package com.lxk.thread.cas;

/**
 * @author lxk on 2018/5/3
 */
public class Main {
    public static void main(String[] args) {
        Runnable run = new CASCount();

        new Thread(run).start();
        new Thread(run).start();
        new Thread(run).start();
        new Thread(run).start();
        new Thread(run).start();
        new Thread(run).start();
        new Thread(run).start();
        new Thread(run).start();
        new Thread(run).start();
        new Thread(run).start();
    }
}
