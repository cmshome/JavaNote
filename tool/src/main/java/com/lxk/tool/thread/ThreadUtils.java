package com.lxk.tool.thread;

import com.lxk.tool.print.PrintUtil;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Thread 公共方法
 *
 * @author LiXuekai on 2019/10/8
 */
public class ThreadUtils {

    /**
     * 暂停一下
     *
     * @param sleep int
     */
    public static void sleep(int sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印线程池的一些信息
     *
     * @param executor pool
     */
    public static void printPoolInfo(ThreadPoolExecutor executor) {
        //the core number of threads.
        System.out.println("getCorePoolSize: " + executor.getCorePoolSize());
        //the current number of threads in the pool.
        System.out.println("getPoolSize：" + executor.getPoolSize());
        //正在执行任务到线程个数
        System.out.println("getActiveCount: " + executor.getActiveCount());
        //已经完成的任务个数
        System.out.println("getCompletedTaskCount: " + executor.getCompletedTaskCount());
        //线程池最大个数
        System.out.println("getLargestPoolSize: " + executor.getLargestPoolSize());
        //被安排的任务总数
        System.out.println("getTaskCount: " + executor.getTaskCount());
        //线程池最大的线程数
        System.out.println("getMaximumPoolSize: " + executor.getMaximumPoolSize());
        //队列中等待执行的任务
        System.out.println("getQueue: " + executor.getQueue());
        PrintUtil.divideLine();
    }

    /**
     * 打印线程池的一些信息
     *
     * @param executor pool
     */
    public static void printPoolInfo(ScheduledThreadPoolExecutor executor) {
        //the core number of threads.
        System.out.println("getCorePoolSize: " + executor.getCorePoolSize());
        //the current number of threads in the pool.
        System.out.println("getPoolSize：" + executor.getPoolSize());
        //正在执行任务到线程个数
        System.out.println("getActiveCount: " + executor.getActiveCount());
        //已经完成的任务个数
        System.out.println("getCompletedTaskCount: " + executor.getCompletedTaskCount());
        //线程池最大个数
        System.out.println("getLargestPoolSize: " + executor.getLargestPoolSize());
        //被安排的任务总数
        System.out.println("getTaskCount: " + executor.getTaskCount());
        //线程池最大的线程数
        System.out.println("getMaximumPoolSize: " + executor.getMaximumPoolSize());
        //队列中等待执行的任务
        System.out.println("getQueue: " + executor.getQueue());


        //这个是定时任务线程池这个类特有的
        System.out.println(": " + executor.getRemoveOnCancelPolicy());
        System.out.println(": " + executor.getContinueExistingPeriodicTasksAfterShutdownPolicy());
        System.out.println(": " + executor.getExecuteExistingDelayedTasksAfterShutdownPolicy());


        PrintUtil.divideLine();
    }

}
