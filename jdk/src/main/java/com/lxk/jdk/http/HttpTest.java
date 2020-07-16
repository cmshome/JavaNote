package com.lxk.jdk.http;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lxk.tool.HttpUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * http test
 *
 * @author LiXuekai on 2020/7/15
 */
public class HttpTest {

    /**
     * post 带参数
     */
    @Test
    public void once() throws IOException {
        String url = "http://192.168.100.127:8088/refresh";
        Map<String, String> paramMap = Maps.newHashMapWithExpectedSize(2);
        paramMap.put("type", "AsyncStream");
        HttpUtils.HttpClientResult httpClientResult = HttpUtils.doPost(url, paramMap);
        System.out.println(httpClientResult.getCode());
        System.out.println(httpClientResult.getContent());
    }

    @Test
    public void testClose() {
        initCloseEvent();
        System.out.println("----");
    }

    /**
     * 在JVM销毁前执行的一个线程
     */
    private void initCloseEvent() {
        ScheduledExecutorService monitorSchedule = new ScheduledThreadPoolExecutor(
                1, new ThreadFactoryBuilder().setNameFormat(
                "import-user-thread-pool").build(),
                new ThreadPoolExecutor.AbortPolicy());
        monitorSchedule.scheduleWithFixedDelay(this::importUerInfo, 0, 1, TimeUnit.HOURS);

        Runtime.getRuntime().addShutdownHook(new Thread("do-when-jvm-is-shut-down") {
            @Override
            public void run() {
                monitorSchedule.shutdown();
                System.out.println("shutdown program");
            }
        });
    }

    private void importUerInfo() {
        System.out.println("import user");
    }
}
