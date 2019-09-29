package com.lxk.thread.join.case2;

/**
 * @author lxk on 2018/4/8
 */
public class ThreadTest implements Runnable {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(this.getName() + "------" + i);
        }
    }

}
