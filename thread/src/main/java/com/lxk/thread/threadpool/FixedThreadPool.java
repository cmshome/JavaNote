package com.lxk.thread.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * newFixedThreadPool(N)
 * <p>
 * 创建可重用且固定线程数的线程池，
 * 如果线程池中的所有线程都处于活动状态，此时再提交任务就在队列中等待，直到有可用线程；
 * 如果线程池中的某个线程由于异常而结束时，线程池就会再补充一条新线程。
 *
 * @author lxk on 2018/4/18
 */
public class FixedThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);

        //return new ThreadPoolExecutor(nThreads, nThreads,
        //        0L, TimeUnit.MILLISECONDS,
        //        new LinkedBlockingQueue<Runnable>());

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("BasicThreadPoolJob-CachedThreadPool-%d").build();
        ExecutorService executor = new ThreadPoolExecutor(6,
                10,
                5,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        service.execute(() -> System.out.println("HeartBeat........................."));
        service.shutdown();



    }
}
