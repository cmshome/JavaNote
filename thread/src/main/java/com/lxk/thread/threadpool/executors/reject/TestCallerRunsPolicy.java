package com.lxk.thread.threadpool.executors.reject;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * CallerRunsPolicy 当任务添加到线程池中被拒绝时，会在线程池当前正在运行的Thread线程池中处理被拒绝的任务
 * 翻译一下：就是不进入线程池执行，在这种方式（CallerRunsPolicy）中，任务将有调用者线程去执行
 *
 * @author LiXuekai on 2020/3/27
 */
public class TestCallerRunsPolicy {

    private static final int THREADS_SIZE = 1;
    private static final int TASK_MAX = 10;
    private static final int CAPACITY = 1;

    public static void main(String[] args) throws Exception {

        // 创建线程池。线程池的"最大池大小"和"核心池大小"都为1(THREADS_SIZE)，"线程池"的阻塞队列容量为1(CAPACITY)。
        ThreadPoolExecutor pool = new ThreadPoolExecutor(THREADS_SIZE, THREADS_SIZE, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(CAPACITY));
        // 设置线程池的拒绝策略为"CallerRunsPolicy"
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

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
