package com.lxk.thread.multithreadCommunication.inOutDemo.good;

/**
 * @author lxk on 2017/6/27
 */
public class Output implements Runnable {
    private Resources r;

    Output(Resources r) {
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {
            r.out();
        }
    }
}