package com.lxk.jdk.http;

import com.google.common.collect.Maps;
import com.lxk.tool.HttpUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

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
        Runtime.getRuntime().addShutdownHook(new Thread("shut-Consumers") {
            @Override
            public void run() {
                System.out.println("shutdown program");
            }
        });
    }
}
