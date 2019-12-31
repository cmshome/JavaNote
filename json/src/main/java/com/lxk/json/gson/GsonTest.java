package com.lxk.json.gson;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lxk.tool.json.JsonUtils;
import org.junit.Test;

import java.util.Map;

/**
 * @author LiXuekai on 2019/12/31
 */
public class GsonTest {
    /**
     * json中key重复，【会异常】
     */
    @Test
    public void repeatKeyTest() {
        String json = "{\n" +
                "\"s.os\":\"....#\",\n" +
                "\"s.os\":\"....#\",\n" +
                "\"ssass\":\"\",\n" +
                "\"lxk\":123467987654345\n" +
                "}";
        //map 或者 hash map 都失败com.google.gson.JsonSyntaxException: duplicate key: s.os
        Map jsonObj;
        try {
            jsonObj = new Gson().fromJson(json, Map.class);

        } catch (Exception e) {
            if (e instanceof JsonSyntaxException) {
                System.out.println("----");
            }
            System.out.println(e.getMessage());
            jsonObj = JsonUtils.parseJsonToObj(json, Map.class);
        }
        System.out.println(jsonObj);
    }
}
