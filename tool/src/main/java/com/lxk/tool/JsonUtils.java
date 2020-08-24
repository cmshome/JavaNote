package com.lxk.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * JSON 转换
 *
 * @author LiXuekai on 2019/7/4
 */
public final class JsonUtils {

    /**
     * 把Java对象转换成json字符串
     *
     * @param object 待转化为JSON字符串的Java对象
     * @return json 串 or null
     */
    public static <T> String parseObjToJson(T object) {
        String string = null;
        try {
            string = JSON.toJSONString(object);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return string;
    }

    /**
     * 将Json字符串信息转换成对应的Java对象
     * StringEscapeUtils.unescapeJavaScript(json); 一个反斜杠好说，要是1个2个的，不是很好用的感觉。
     *
     * @param json json字符串对象
     * @param c    对应的类型
     */
    public static <T> T parseJsonToObj(String json, Class<T> c) {
        try {
            JSONObject jsonObject = JSON.parseObject(json);
            return JSON.toJavaObject(jsonObject, c);
        } catch (Exception e) {
            try {
                String s = json.replace("\\", "");
                JSONObject jsonObject = JSON.parseObject(s);
                return JSON.toJavaObject(jsonObject, c);
            } catch (Exception ee) {
                System.out.println(ee.getMessage());
            }
        }
        return null;
    }

    /**
     * 输出格式化的json字符串
     */
    public static <T> String parseObjToFormatJson(T object) {
        return JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.PrettyFormat);
    }

    /**
     * 不格式化Json数据
     */
    public static <T> String parseObjToNoFormatJson(T object) {
        return JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);
    }
}
