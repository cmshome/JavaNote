package com.lxk.thread.multithreadCommunication.inOutDemo.bad;

/**
 * @author lxk on 2017/6/27
 */
public class Output implements Runnable {
    private final Resources r;

    Output(Resources r) {
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (r) {
                if (!r.flag) {
                    try {
                        r.wait();
                    } catch (Exception ignore) {
                    }
                }
                System.out.println(r.name + "...." + r.sex);
                r.flag = false;
                r.notify();
            }
        }
    }
}