package com.lxk.thread.multithreadCommunication.inOutDemo.bad;

/**
 * @author lxk on 2017/6/27
 */
public class Input implements Runnable {
    private final Resources r;

    Input(Resources r) {
        this.r = r;
    }

    @Override
    public void run() {
        int x = 0;
        while (true) {
            synchronized (r) {

                if (r.flag) {
                    try {
                        r.wait();
                    } catch (Exception ignore) {
                    }
                }

                if (x == 0) {
                    r.name = "mike";
                    r.sex = "man";
                } else {
                    r.name = "丽丽";
                    r.sex = "女女女女女";
                }
                x = (x + 1) % 2;
                r.flag = true;
                r.notify();
            }
        }
    }
}
