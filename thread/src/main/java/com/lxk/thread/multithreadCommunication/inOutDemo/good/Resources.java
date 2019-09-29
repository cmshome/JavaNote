package com.lxk.thread.multithreadCommunication.inOutDemo.good;

/**
 * @author lxk on 2017/6/27
 */
public class Resources {
    private String name;
    private String sex;
    private boolean flag = false;

    public synchronized void set(String name, String sex) {
        if (flag) {
            try {
                this.wait();
            } catch (Exception ignore) {
            }
        }
        this.name = name;
        this.sex = sex;
        this.flag = true;
        this.notify();
    }

    public synchronized void out() {
        if (!this.flag) {
            try {
                this.wait();
            } catch (Exception ignore) {
            }
        }
        System.out.println(this.name + "...." + this.sex);
        flag = false;
        notify();
    }
}