package com.lxk.thread.multithreadCommunication.producerConsumer.good;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lxk on 2017/6/27
 */
public class Resource {
    private String name;
    private int count = 1;
    private boolean flag = false;
    private Lock lock = new ReentrantLock();
    private Condition producerCondition = lock.newCondition();
    private Condition consumerCondition = lock.newCondition();

    //  t1    t2
    public void set(String name) throws InterruptedException {
        lock.lock();
        try{
            //if(flag)//这么写还是会bug，可能多消费，或者多生产。
            while(flag) {
                producerCondition.await();//t1,t2
            }
            this.name = name+"--"+count++;

            System.out.println(Thread.currentThread().getName()+"...生产者.."+this.name);
            flag = true;
            consumerCondition.signal();
        } finally {
            lock.unlock();//释放锁的动作一定要执行。
        }

    }


    //  t3   t4
    public void out() throws InterruptedException {
        lock.lock();
        try {
            //if(!flag)//用if还是 会出现bug。
            while (!flag) {
                consumerCondition.await();//t3(放弃资格) t4(放弃资格)
            }
            System.out.println(Thread.currentThread().getName() + "...消费者........." + this.name);
            flag = false;
            producerCondition.signal();
        } finally {
            lock.unlock();
        }
    }
}
