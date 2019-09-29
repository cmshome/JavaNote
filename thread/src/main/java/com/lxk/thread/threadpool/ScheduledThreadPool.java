package com.lxk.thread.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * newScheduledThreadPool(N)
 * <p>
 * 创建一个可延迟执行或定期执行的线程池
 *
 * @author lxk on 2018/4/18
 */
public class ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

        //return new FinalizableDelegatedExecutorService
        //        (new ThreadPoolExecutor(1, 1,
        //                0L, TimeUnit.MILLISECONDS,
        //                new LinkedBlockingQueue<Runnable>()));

        System.out.println("5秒后第一次执行，之后每隔3秒执行一次");
        Runnable task = () -> System.out.println("HeartBeat.........................");
        //Runnable task = new Runnable() {
        //    @Override
        //    public void run() {
        //        System.out.println("HeartBeat.........................");
        //    }
        //
        //};
        //5秒后第一次执行，之后每隔3秒执行一次

        executor.scheduleAtFixedRate(task, 5, 3, TimeUnit.SECONDS);
        //executor.shutdown();
    }
}

