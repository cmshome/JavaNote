package com.lxk.json.fastjson;

import com.alibaba.fastjson.parser.ParserConfig;
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

    @Test
    public void t() {
        String s = "{\"mapping\":{\"transRef\":{\"@type\":\"string\"}},\"start_at\":1610513218,\"stream\":\"5f968721303e482cf6dd344b\"}";
        Map map = JsonUtils.parseJsonToObj(s, Map.class);
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        System.out.println(map.size());
    }

}
