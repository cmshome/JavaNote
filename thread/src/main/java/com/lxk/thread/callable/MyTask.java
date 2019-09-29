package com.lxk.thread.callable;

import java.util.concurrent.Callable;

/**
 * @author lxk on 2018/3/22
 */
class MyTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }
}