package com.lxk.tool.monitor;

import org.junit.Test;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author LiXuekai on 2021/2/2
 */
public class TestMonitorService {

    @Test
    public void test() throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = MonitorService.getScheduledExecutorService();
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " running...");

            });
            scheduledExecutorService.scheduleWithFixedDelay(thread, 10, 10, TimeUnit.SECONDS);
        }

        TimeUnit.MINUTES.sleep(5);
    }
}
