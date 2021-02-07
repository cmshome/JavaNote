package com.lxk.jdk.jvm.memory;

import com.lxk.jdk.jvm.memory.model.JMM;
import com.lxk.tool.JsonUtils;
import org.junit.Test;

/**
 * main 方法
 *
 * @author LiXuekai on 2019/12/31
 */
public class MainTest {

    /**
     * 用对象的形式把JMM梳理一下
     */
    @Test
    public void jvm() {
        JMM jmm = new JMM();
        System.out.println(JsonUtils.parseObjToFormatJson(jmm));
    }
}
