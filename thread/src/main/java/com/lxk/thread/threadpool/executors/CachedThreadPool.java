package com.lxk.thread.threadpool.executors;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lxk.tool.thread.ThreadUtils;

import java.util.concurrent.*;

/**
 * 创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
 * 当拒绝处理任务时的策略-ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
 * core size是0，那么这个线程池创建的线程都是来个任务就创建线程，没有上限限制的。很危险的。
 * 只要 pool size 不到 Integer.MAX_VALUE，且无可用的线程，他就能一直创建新的线程。
 * 下面的例子，1000个runnable几乎一起提交且都至少执行10秒，所以，池中无可用线程，就只能自己再造个新的，自己用。然后就造了1000个。
 * 还可以继续大
 * 你把max改成Integer.MAX_VALUE，运行一下，就OOM了。因为要一下子创建很大数量的线程，内存不够使用了。
 * 厉害了。
 *
 * @author lxk on 2018/10/8
 */
public class CachedThreadPool {
    private static final int MAX = 1000;

    public static void main(String[] args) {
        ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        //return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
        //        60L, TimeUnit.SECONDS,
        //        new SynchronousQueue<Runnable>());

        //即
        //this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
        //        executors.defaultThreadFactory(), defaultHandler);

        // defaultHandler = new AbortPolicy();

        for (int i = 0; i < MAX; i++) {
            int index = i + 1;
            service.execute(() -> {
                //每个线程至少执行10秒
                ThreadUtils.sleep(10000);
                System.out.println("HeartBeat......................... index is " + index);
            });
        }
        ThreadUtils.printPoolInfo(service);
        System.out.println("ok");
        service.shutdown();
    }

    /**
     * 推荐初始化线程池的方式
     */
    private static void positive() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("CachedThreadPool-%d").build();
        ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());
    }
}
