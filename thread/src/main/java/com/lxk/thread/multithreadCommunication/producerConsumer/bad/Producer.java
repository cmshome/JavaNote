package com.lxk.thread.multithreadCommunication.producerConsumer.bad;

/**
 * @author lxk on 2017/6/27
 */
public class Producer implements Runnable {
    private Resource res;

    Producer(Resource res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (true) {
            res.set("+商品+");
        }
    }
}
