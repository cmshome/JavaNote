package com.lxk.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.lxk.json.model.Cup;
import com.lxk.tool.JsonUtils;
import org.junit.Test;

import java.util.List;
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


    /**
     * 因为key之前多了个 \ 导致解码失败了。
     * fastjson 右斜杠 简单粗暴的直接给他replace了吧。
     */
    @Test
    public void unclosedString() {
        String json = "{\"s.os\":\"李\\学23456...#\",\"ssass\":\"\",\"lxk\":123467987654345}";
        System.out.println(json);
        //Map 类可以， hash map 不 OK。
        Map map = JsonUtils.parseJsonToObj(json, Map.class);
        System.out.println(map);
    }

    /**
     * 因为key之前多了个 \ 导致解码失败了。
     * 不管多少个反斜杠，简单粗暴的给他全替换了得了
     * StringEscapeUtils.unescapeJavaScript(json);
     * 这个不太好使。
     */
    @Test
    public void unclosedString2() {
        String json = "{\"s.os\":\"李\\\\\\\\\\\\学\\十大!@#$%^&*()))_+{}:?><{}][|||||{{{}}}}[][][][[[]]]:::;;;''''''\\~`表代表///////23456...#\",\"ssass\":\"\",\"lxk\":123467987654345}";
        System.out.println(json);
        //Map 类可以， hash map 不 OK。
        Map map = JsonUtils.parseJsonToObj(json, Map.class);
        System.out.println(map);
    }

    /**
     * json中key是 @type 的，fastjson会解析异常。
     */
    @Test
    public void t() {
        String s = "{\"mapping\":{\"transRef\":{\"@type\":\"string\"}},\"start_at\":1610513218,\"stream\":\"5f968721303e482cf6dd344b\"}";
        Map map = JsonUtils.parseJsonToObj(s, Map.class);
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        System.out.println(map.size());
    }

    /**
     * json中key是 @type 的，fastjson会解析异常。
     */
    @Test
    public void ss() {
        String json = "{\"mapping\":{\"transRef\":{\"@type\":\"string\"}},\"start_at\":1610513218,\"stream\":\"5f968721303e482cf6dd344b\"}";
        JSONObject jsonObject = JSON.parseObject(json);
    }

    /**
     * json中key是 @type 的，fastjson会解析异常。这个对象的map里面的可以是 @type导致的异常。
     */
    @Test
    public void parseArray() {
        String json = Cup.getArrayJson();
        List<Cup> list = JSON.parseArray(json, Cup.class);
        list.forEach(cup -> {
            System.out.println(cup.toString());
        });
    }
}
