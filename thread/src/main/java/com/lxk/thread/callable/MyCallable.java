package com.lxk.thread.callable;

import java.util.concurrent.Callable;

/**
 * 实现Callable<T>接口来实现多线程
 * <p>
 * @author lxk on 2017/6/29
 */
public class MyCallable implements Callable<String> {
    private int flag = 0;

    public MyCallable(int flag) {
        this.flag = flag;
    }

    @Override
    public String call() throws Exception {
        if (this.flag == 0) {
            return "flag = 0";
        }
        if (this.flag == 1) {
            try {
                while (true) {
                    System.out.println("looping.");
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
            return "false";
        } else {
            throw new Exception("Bad flag value!");
        }
    }
}
