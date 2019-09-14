package com.lxk.jdk.system;

import org.junit.Test;

import java.util.Map;
import java.util.Properties;

/**
 * @author lxk on 2017/12/18
 */
public class SystemTest {

    /**
     * 打印 System.getProperties() 的键值对
     */
    @Test
    public void testGetProperties() {
        Properties prop = System.getProperties();
        //54个
        System.out.println("获得的键值对的总个数：" + prop.size());
        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            System.out.println("键：" + entry.getKey() + " 值：" + entry.getValue());
        }
    }

    /**
     * 单独测试一些特殊的key
     * <p>
     * System.getProperty("user.dir")
     * 获得的是工作目录，非Java web项目的运行结果，就是当前项目的根目录。
     * 此处是：E:\fusion\intellij_work\HelloWorld
     * java web 项目的运行结果则是：tomcat的bin目录。
     * <p>
     * System.getProperty("sun.cpu.isalist")
     * 说是获得系统的CPU信息，为啥我这地方的返回结果是amd64，我电脑的CPU是 i5 啊
     */
    @Test
    public void testSystemGetSpecialKey() {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("sun.cpu.isalist"));
    }
}
