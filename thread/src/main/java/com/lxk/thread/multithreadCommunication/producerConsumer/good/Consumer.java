package com.lxk.thread.multithreadCommunication.producerConsumer.good;

/**
 * @author lxk on 2017/6/27
 */
public class Consumer implements Runnable {
    private Resource res;

    Consumer(Resource res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (true) {
            try {
                res.out();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
