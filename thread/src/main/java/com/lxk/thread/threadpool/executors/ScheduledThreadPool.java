package com.lxk.thread.threadpool.executors;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

/**
 * newScheduledThreadPool(N)
 * <p>
 * 创建一个可延迟执行或定期执行的线程池
 * 他这个缓存任务的队列也是很大的
 *
 * @author lxk on 2018/4/18
 */
public class ScheduledThreadPool {

    private static final int CORE_SIZE = 5;
    private static final int MAX = 1;

    public static void main(String[] args) {
        //ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) positive();

        //return new ScheduledThreadPoolExecutor(corePoolSize);

        //super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
        //        new DelayedWorkQueue());

        //public ThreadPoolExecutor(int corePoolSize,
        //                          int maximumPoolSize,
        //                          long keepAliveTime,
        //                          TimeUnit unit,
        //                          BlockingQueue<Runnable> workQueue) {
        //    this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
        //            Executors.defaultThreadFactory(), defaultHandler);
        //}

        DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");
        Runnable task = () -> {
            //ThreadUtils.sleep(2000);
            System.out.println("HeartBeat......................... index is " + LocalTime.now().format(simpleDateFormat));
        };
        executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);

        //System.out.println("0秒后第一次执行，之后每隔3秒执行一次");
        //for (int i = 0; i < MAX; i++) {
        //    int index = i + 1;
        //    Runnable task = () -> System.out.println("HeartBeat......................... index is " + index);
        //    executor.scheduleAtFixedRate(task, 10, 20, TimeUnit.SECONDS);
        //}
        //ThreadUtils.printPoolInfo(executor);
        //
        //System.out.println("ok");

        //executor.shutdown();
    }


    /**
     * 推荐初始化线程池的方式
     */
    private static ThreadPoolExecutor positive() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("ScheduledThreadPool-%d").build();
        //ExecutorService executor = new ThreadPoolExecutor(5, Integer.MAX_VALUE, 0L, TimeUnit.NANOSECONDS,
        //        new DelayedWorkQueue<>(),
        //        namedThreadFactory,
        //        new ThreadPoolExecutor.AbortPolicy());

        //这个使用了一个内部类 DelayedWorkQueue ，外部无法创建 直接使用 ThreadPoolExecutor 创建。。。

        //ThreadPoolExecutor monitorSchedule = new ScheduledThreadPoolExecutor(1, namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        ScheduledThreadPoolExecutor monitorSchedule = new ScheduledThreadPoolExecutor(CORE_SIZE, namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        return monitorSchedule;

    }


    /**
     * 创建一个core线程，多余的都在队列等着。
     */
    private static void single(){
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    }
}

