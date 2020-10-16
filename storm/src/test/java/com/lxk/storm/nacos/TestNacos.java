package com.lxk.storm.nacos;

import com.lxk.storm.service.NacosService;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author LiXuekai on 2020/10/15
 */
public class TestNacos {


    @Before
    public void init() {
        String serverAddr = "192.168.1.191:8848";
        String nameSpace = "test";
        NacosService.initNacosServer(serverAddr, nameSpace);
    }

    /**
     * 把bolt需要的数据造到nacos上
     */
    @Test
    public void pushConfig() {
        String group = "storm";

        String dataId = "allUseMetrics";
        String content = "";
        NacosService.pushConfig(content, dataId, group);
    }

    @Test
    public void get() {
        String s = NacosService.get("allUseMetrics", "storm");
        System.out.println(s);
    }

    @Test
    public void callback() throws InterruptedException {
        NacosService.addListener("allUseMetrics", "storm", this::out);
        TimeUnit.MINUTES.sleep(5);
    }

    private void out(String s) {
        System.out.println("更新了，callback 。。。" + s);
    }


}
