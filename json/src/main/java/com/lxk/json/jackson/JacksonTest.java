package com.lxk.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LiXuekai on 2019/12/31
 */
public class JacksonTest {

    /**
     * json中key重复，【不会异常】
     */
    @Test
    public void repeatKeyTest() throws IOException {
        String json = "{\n" +
                "\"s.os\":\"....#\",\n" +
                "\"s.os\":\"..3456..#\",\n" +
                "\"ssass\":\"\",\n" +
                "\"lxk\":123467987654345\n" +
                "}";
        ObjectMapper om = new ObjectMapper();

        //map 或者 hash map 都 ok
        Map jsonObj = om.readValue(json, HashMap.class);
        System.out.println(jsonObj);
    }
}
