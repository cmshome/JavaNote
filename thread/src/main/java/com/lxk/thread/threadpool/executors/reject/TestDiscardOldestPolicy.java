package com.lxk.thread.threadpool.executors.reject;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 当任务添加到线程池中被拒绝时，线程池会放弃等待队列中最旧的未处理任务，然后将被拒绝的任务添加到等待队列中。
 * <p>
 * 结果说明：
 * 将"线程池的拒绝策略"由DiscardPolicy修改为DiscardOldestPolicy之后，当有任务添加到线程池被拒绝时，
 * 线程池会丢弃阻塞队列中末尾的任务，然后将被拒绝的任务添加到末尾。
 *
 * @author LiXuekai on 2020/3/27
 */
public class TestDiscardOldestPolicy {

    private static final int THREADS_SIZE = 1;
    private static final int TASK_MAX = 10;
    private static final int CAPACITY = 1;

    public static void main(String[] args) throws Exception {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("Test-Rejected-Policy-Pool-%d").build();

        // 创建线程池。线程池的"最大池大小"和"核心池大小"都为1(THREADS_SIZE)，"线程池"的阻塞队列容量为1(CAPACITY)。
        ThreadPoolExecutor pool = new ThreadPoolExecutor(THREADS_SIZE, THREADS_SIZE, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(CAPACITY), namedThreadFactory);
        // 设置线程池的拒绝策略为"DiscardOldestPolicy"
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());

        // 新建10个任务，并将它们添加到线程池中。
        for (int i = 0; i < TASK_MAX; i++) {
            Runnable myRunnable = new MyRunnable("task [ " + i + " ]");
            try {
                pool.execute(myRunnable);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        // 关闭线程池
        pool.shutdown();
    }
}
