package com.lxk.thread.threadpool.executors.reject;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * DiscardOldestPolicy -- 当任务添加到线程池中被拒绝时，线程池会放弃等待队列中最旧的未处理任务，然后将被拒绝的任务添加到等待队列中。
 * 结果说明：线程池pool的"最大池大小"和"核心池大小"都为1(THREADS_SIZE)，这意味着"线程池能同时运行的任务数量最大只能是1"。
 * 线程池pool的阻塞队列是ArrayBlockingQueue，ArrayBlockingQueue是一个有界的阻塞队列，ArrayBlockingQueue的容量为1。这也意味着线程池的阻塞队列只能有一个线程池阻塞等待。
 * 根据""中分析的execute()代码可知：线程池中共运行了2个任务。第1个任务直接放到Worker中，通过线程去执行；第2个任务放到阻塞队列中等待。其他的任务都被丢弃了！
 *
 * @author LiXuekai on 2020/3/27
 */
public class TestDiscardPolicy {

    private static final int THREADS_SIZE = 1;
    private static final int TASK_MAX = 10;
    private static final int CAPACITY = 1;

    public static void main(String[] args) throws Exception {

        // 创建线程池。线程池的"最大池大小"和"核心池大小"都为1(THREADS_SIZE)，"线程池"的阻塞队列容量为1(CAPACITY)。
        ThreadPoolExecutor pool = new ThreadPoolExecutor(THREADS_SIZE, THREADS_SIZE, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(CAPACITY));
        // 设置线程池的拒绝策略为"丢弃"
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());

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
