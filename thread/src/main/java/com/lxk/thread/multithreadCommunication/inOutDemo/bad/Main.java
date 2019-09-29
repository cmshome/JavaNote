package com.lxk.thread.multithreadCommunication.inOutDemo.bad;

/**
 * 两个线程，一个负责写，一个负责读，交互着来。
 * （未优化的代码，易于理解。）
 *
 * @author lxk on 2017/6/27
 */
public class Main {
    public static void main(String[] args) {
        Resources r = new Resources();

        Input in = new Input(r);
        Output out = new Output(r);

        Thread t1 = new Thread(in);
        Thread t2 = new Thread(out);

        t1.start();
        t2.start();
    }
}
