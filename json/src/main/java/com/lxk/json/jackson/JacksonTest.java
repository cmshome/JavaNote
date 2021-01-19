package com.lxk.json.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lxk.json.model.Cup;
import com.lxk.tool.JsonUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiXuekai on 2019/12/31
 */
public class JacksonTest {
    private final ObjectMapper mapper = new ObjectMapper();
    private String json;

    @Before
    public void init() {
        json = Cup.getArrayJson();
    }

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

        //map 或者 hash map 都 ok
        Map jsonObj = mapper.readValue(json, HashMap.class);
        System.out.println(jsonObj);
    }

    @Test
    public void test() throws IOException {
        String s = "{\"mapping\":{\"transRef\":{\"@type\":\"string\"}},\"start_at\":1610513218,\"stream\":\"5f968721303e482cf6dd344b\"}";
        Map map = mapper.readValue(s, HashMap.class);
        System.out.println(map.size());
    }


    @Test
    public void parseArray() throws IOException {
        List<Cup> list = mapper.readValue(json, new TypeReference<List<Cup>>() { });
        list.forEach(cup -> {System.out.println(cup.toString());});
    }

    @Test
    public void parseArray2() {
        List<Cup> list = JsonUtils.parseJsonToArrayObj(json, Cup.class);
        list.forEach(cup -> {System.out.println(cup.toString());});
    }

}
