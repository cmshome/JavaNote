package com.lxk.thread.threadpool.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lxk.tool.thread.ThreadUtils;

import java.util.concurrent.*;

/**
 * 测试线程池中线程的增减
 *
 * @author LiXuekai on 2019/9/30
 */
public class PoolTest {
    private static final int CORE_SIZE = 5;
    private static final int MAX_POOL_SIZE = 7;
    private static final int MAX = 10;
    private static final int KEEP_ALIVE_TIME = 10;
    private static ThreadPoolExecutor executor = initExecutor();


    /**
     * 因为之前测试循环栅栏的时候，使用junit测试出现意想不到的结果，这还是使用main方法测试吧。
     */
    public static void main(String[] args) {
        printPoolInfo();
        testThreadPool();
        //executor.shutdown();
    }

    /**
     * 测试线程池中线程的不同情况的状态
     */
    private static void testThreadPool() {
        for (int i = 0; i < MAX; i++) {
            int index = i + 1;
            executor.execute(() -> {
                System.out.println("execute one Runnable, index is " + index);
                //每个线程执行完成需要至少x秒
                ThreadUtils.sleep(10000);
            });
            ThreadUtils.sleep(1000);
            printPoolInfo();
        }
        initScheduledExecutorService();
    }


    /**
     * 打印线程池的一些信息
     */
    private static void printPoolInfo() {
        ThreadUtils.printPoolInfo(executor);
    }

    /**
     * 初始化线程池 类似 {@link Executors#newCachedThreadPool()}
     * new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), defaultThreadFactory, AbortPolicy());
     * new ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
     *                               BlockingQueue<Runnable> workQueue,
     *                               ThreadFactory threadFactory,
     *                               RejectedExecutionHandler handler)
     */
    private static ThreadPoolExecutor initExecutor() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("my-ThreadPool-%d").build();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new SynchronousQueue<>(),
                namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        //设置线程池中的core线程是否会过期销毁，默认就是false。
        executor.allowCoreThreadTimeOut(false);
        //提前创建一个core线程
        executor.prestartCoreThread();
        //提前把所有core线程都创建了
        executor.prestartAllCoreThreads();
        return executor;
    }

    /**
     * 启动定时任务，监视pool的信息
     */
    private static void initScheduledExecutorService() {
        ThreadFactory monitorThreadFactory = new ThreadFactoryBuilder().setNameFormat("Monitor-Thread-Pool").build();
        ScheduledExecutorService monitorSchedule = new ScheduledThreadPoolExecutor(1, monitorThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        monitorSchedule.scheduleWithFixedDelay(PoolTest::monitorSchedule, 0, 1, TimeUnit.SECONDS);
    }

    private static void monitorSchedule() {
        System.out.println("监控线程池状态的任务打印。。。。。。。。。");
        printPoolInfo();
    }

}
