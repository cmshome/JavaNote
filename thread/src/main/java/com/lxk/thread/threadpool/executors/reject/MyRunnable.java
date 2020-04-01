package com.lxk.thread.threadpool.executors.reject;

/**
 * @author LiXuekai on 2020/3/27
 */
public class MyRunnable implements Runnable {
    private String name;

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            String name = "当前线程名称：" + Thread.currentThread().getName();
            System.out.println(name + ", " + this.name + " is running.");
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
