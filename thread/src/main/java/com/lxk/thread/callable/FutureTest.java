package com.lxk.thread.callable;

import java.util.concurrent.*;

/**
 * 使用Callable+Future获取执行结果
 *
 * @author lxk on 2018/3/22
 */
public class FutureTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        MyTask myTask = new MyTask();
        Future<Integer> result = executor.submit(myTask);
        executor.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            System.out.println("task运行结果" + result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }
}


