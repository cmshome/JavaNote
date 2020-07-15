package com.lxk.json.fastjson;

import com.lxk.tool.JsonUtils;
import org.junit.Test;

import java.util.Map;

/**
 * @author LiXuekai on 2019/12/31
 */
public class FastJsonTest {
    /**
     * json中key重复，【不会异常】
     */
    @Test
    public void repeatKeyTest() {
        String json = "{\n" +
                "\"s.os\":\"....#\",\n" +
                "\"s.os\":\".23456...#\",\n" +
                "\"ssass\":\"\",\n" +
                "\"lxk\":123467987654345\n" +
                "}";
        //Map 类可以， hash map 不 OK。
        Map map = JsonUtils.parseJsonToObj(json, Map.class);
        System.out.println(map);
    }
}
