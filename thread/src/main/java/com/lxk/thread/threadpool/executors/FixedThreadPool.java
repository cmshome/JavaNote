package com.lxk.thread.threadpool.executors;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lxk.tool.thread.ThreadUtils;

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
    private static final int N_THREADS = 2;
    private static final int MAX = 4;

    public static void main(String[] args) {
        ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newFixedThreadPool(N_THREADS);

        //return new ThreadPoolExecutor(nThreads, nThreads,
        //        0L, TimeUnit.MILLISECONDS,
        //        new LinkedBlockingQueue<Runnable>());

        //this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
        //        Executors.defaultThreadFactory(), defaultHandler);

        //defaultHandler = new AbortPolicy();

        //new LinkedBlockingQueue<Runnable>()
        //这个链表队列初始化的时候，初始化了 【Integer.MAX_VALUE】 这么长的一个队列，来缓存暂时没有可用线程的任务。
        //所以，这个线程池也会缓存 很多的带执行的任务，导致OOM
        //MAX = Integer.MAX_VALUE
        //改了之后，没有像之前那样瞬间OOM，但是电脑却是嗡嗡的开始响了，很是占cpu。

        // public LinkedBlockingQueue() {
        //        this(Integer.MAX_VALUE);
        //    }

        for (int i = 0; i < MAX; i++) {
            int index = i + 1;
            service.execute(() -> {
                //每个线程至少执行10秒
                ThreadUtils.sleep(10000);
                System.out.println("Heart Beat......................... index is " + index);
            });
            ThreadUtils.sleep(1000);
            ThreadUtils.printPoolInfo(service);
        }
        ThreadUtils.printPoolInfo(service);
        System.out.println("ok");
        service.shutdown();


    }

    /**
     * 推荐初始化线程池的方式
     */
    private static void positive() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("FixedThreadPool-%d").build();
        ExecutorService executor = new ThreadPoolExecutor(6, 6, 5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());
    }
}
