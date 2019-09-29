package com.lxk.thread.multithreadCommunication.producerConsumer.bad;

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
            res.out();
        }
    }
}
