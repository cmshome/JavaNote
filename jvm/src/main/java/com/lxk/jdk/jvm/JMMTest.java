package com.lxk.jdk.jvm;

import com.lxk.jdk.jvm.model.JavaMemoryModel;
import com.lxk.tool.json.JsonUtils;
import org.junit.Test;

/**
 * main 方法
 *
 * @author LiXuekai on 2019/12/31
 */
public class JMMTest {

    /**
     * 用对象的形式把JMM梳理一下
     */
    @Test
    public void jvm() {
        JavaMemoryModel JMM = new JavaMemoryModel();
        System.out.println(JsonUtils.parseObjToJson(JMM));
    }
}
