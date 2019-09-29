package com.lxk.thread.multithreadCommunication.producerConsumer.bad;

/**
 * @author lxk on 2017/6/27
 */
public class Resource {
    private String name;
    private int count = 1;
    private boolean flag = false;

    //  t1    t2
    public synchronized void set(String name) {
        //if(flag)//这么写会bug，可能多消费，或者多生产。
        while(flag) {
            try {
                this.wait();
            } catch (Exception e) {
            }//t1(放弃资格)  t2(获取资格)
        }
        this.name = name+"--"+count++;

        System.out.println(Thread.currentThread().getName()+"...生产者.."+this.name);
        flag = true;
        //this.notify();//也会bug，即使修改为while之后，则会四个线程都进入wait。
        this.notifyAll();
    }


    //  t3   t4
    public synchronized void out() {
        //if(!flag)
        while(!flag) {
            try {
                wait();
            } catch (Exception e) {
            }//t3(放弃资格) t4(放弃资格)
        }
        System.out.println(Thread.currentThread().getName()+"...消费者........."+this.name);
        flag = false;
        //this.notify();//也会bug，即使修改为while之后，则会四个线程都进入wait。
        this.notifyAll();
    }
}
