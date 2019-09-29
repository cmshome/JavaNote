package com.lxk.thread.cas;

/**
 * @author lxk on 2018/5/3
 */
public class CASCount implements Runnable {

    private SimilarCAS counter = new SimilarCAS();

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(this.increment());
        }
    }

    public int increment() {
        int oldValue = counter.getValue();
        int newValue = oldValue + 1;

        //如果CAS失败,就去拿新值继续执行CAS
        while (!counter.compareAndSwap(oldValue, newValue)) {
            oldValue = counter.getValue();
            newValue = oldValue + 1;
        }

        return newValue;
    }

}
