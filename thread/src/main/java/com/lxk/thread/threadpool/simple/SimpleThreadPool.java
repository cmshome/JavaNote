package com.lxk.thread.threadpool.simple;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 线程池的使用
 *
 * @author lxk on 2018/7/16
 */
public class SimpleThreadPool {
    public static void main(String[] args) {
        negativePool();
        //positivePool();
    }

    /**
     * 积极的方式（推荐）
     * 避免下面的问题
     */
    private static void positivePool() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(11, 11,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.shutdown();
    }

    /**
     * 消极的方式（不推荐）
     * <p>
     * 创建了包含5个工作线程的固定大小线程池。
     * 向线程池提交10个任务。由于线程池的大小是5，
     * 因此首先会启动5个工作线程，其他任务将进行等待。
     * 一旦有任务结束，工作线程会从等待队列中挑选下一个任务并开始执行
     * <p>
     * 不建议这么使用线程池
     * 1，不清楚具体线程池的各个参数，不利于学习。
     * 2，容易OOM。
     * 3，不能给线程命名，出问题不好排查问题。
     */
    private static void negativePool() {
        ExecutorService executor = getFixedThreadPool();
        for (int i = 0; i < 10; i++) {
            Runnable job = new Job("" + i);
            executor.execute(job);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }

    /**
     * 创建固定容量大小的缓冲池
     */
    private static ExecutorService getFixedThreadPool() {
        return Executors.newFixedThreadPool(5);

        //return new ThreadPoolExecutor(nThreads, nThreads,
        //        0L, TimeUnit.MILLISECONDS,
        //        new LinkedBlockingQueue<Runnable>());
    }

    /**
     * 创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
     */
    private static ExecutorService getCachedThreadPool() {
        return Executors.newCachedThreadPool();

        //return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
        //        60L, TimeUnit.SECONDS,
        //        new SynchronousQueue<Runnable>());
    }

    /**
     * 创建容量为1的缓冲池
     */
    private static ExecutorService getSingleThreadPool() {
        return Executors.newSingleThreadExecutor();

        //return new FinalizableDelegatedExecutorService
        //        (new ThreadPoolExecutor(1, 1,
        //                0L, TimeUnit.MILLISECONDS,
        //                new LinkedBlockingQueue<Runnable>()));
    }


}
