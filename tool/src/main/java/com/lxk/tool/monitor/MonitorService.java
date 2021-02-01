package com.lxk.tool.monitor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Setter;

import java.util.concurrent.*;

/**
 * 监控处理数据情况
 *
 * @author LiXuekai on 2020/12/15
 */
public class MonitorService {
    /**
     * 监控线程的名称，可配置的。
     */
    @Setter
    private static String name = "default";
    /**
     * 监控线程执行间隔时间，可配置。
     */
    @Setter
    private static int delay = 60;
    /**
     * scheduled thread pool core siz，可配置。
     */
    @Setter
    private static int corePoolSize = 10;
    /**
     * 监控线程
     */
    private static MonitorCountThread monitorCountThread;
    /**
     * 线程池
     */
    private static ScheduledExecutorService scheduledExecutorService;


    /**
     * 初始化定时任务线程池单例
     *
     * @return scheduledExecutorService
     */
    private static ScheduledExecutorService getScheduledExecutorService() {
        if (scheduledExecutorService == null) {
            synchronized (MonitorService.class) {
                if (scheduledExecutorService == null) {
                    System.out.println("init ScheduledThreadPool start...");
                    System.out.println("ScheduledThreadPool corePoolSize is " + corePoolSize);
                    ThreadFactory monitorThreadFactory = new ThreadFactoryBuilder().setNameFormat("MonitorServiceThreadPool").build();
                    scheduledExecutorService = new ScheduledThreadPoolExecutor(corePoolSize, monitorThreadFactory, new ThreadPoolExecutor.AbortPolicy());
                    System.out.println("init ScheduledThreadPool success...");
                }
            }
        }
        return scheduledExecutorService;
    }

    /**
     * 初始化监控数量的线程单例
     *
     * @return monitorCountThread
     */
    private static MonitorCountThread getScheduledMonitorThread() {
        if (monitorCountThread == null) {
            synchronized (MonitorService.class) {
                if (monitorCountThread == null) {
                    System.out.println("init scheduled monitorCountThread start...");
                    System.out.println("scheduleWithFixedDelay delay is " + delay +" seconds");
                    monitorCountThread = new MonitorCountThread(name);
                    getScheduledExecutorService().scheduleWithFixedDelay(monitorCountThread, delay, delay, TimeUnit.SECONDS);
                    System.out.println("init scheduled monitorCountThread success ...");
                }
            }
        }
        return monitorCountThread;
    }

    /**
     * 统计单位时间内的 某个key的总数
     *
     * @param key 统计的key
     */
    public static void getAndAdd(String key) {
        getScheduledMonitorThread().getAndAdd(key);
    }



}
