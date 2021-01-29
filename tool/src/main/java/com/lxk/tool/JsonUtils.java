package com.lxk.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.List;

/**
 * JSON 转换
 *
 * @author LiXuekai on 2019/7/4
 */
public final class JsonUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Gson GSON = new Gson();


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
     *
     * @param json json字符串对象
     * @param c    对应的类型
     */
    public static <T> T parseJsonToObj(String json, Class<T> c) {
        T result;
        result = fastjsonCast(json, c);
        if (result != null) {
            return result;
        }

        result = gsonCast(json, c);
        if (result != null) {
            return result;
        }

        result = jacksonCast(json, c);
        if (result != null) {
            return result;
        }

        System.out.println("parseJsonToObj error，json is  " + json);
        return null;
    }

    /**
     * 把json转成对应的list
     *
     * @param json json
     * @param c    class
     * @param <T>  T
     * @return list<T>
     */
    public static <T> List<T> parseJsonToArrayObj(String json, Class<T> c) {
        List<T> list;
        list = fastjsonCastArray(json, c);
        if (list != null) {
            return list;
        }

        list = jacksonCastArray(json, c);
        if (list != null) {
            return list;
        }

        System.out.println("parseJsonToArrayObj error，json is  " + json);
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


    /**
     * fastjson在转换 "@type"为key的json的时候出异常
     */
    private static <T> T fastjsonCast(String json, Class<T> c) {
        try {
            JSONObject jsonObject = JSON.parseObject(json);
            return JSON.toJavaObject(jsonObject, c);
        } catch (Exception e) {
            System.out.println("fastjsonCast parseJsonToObj error " + e.getMessage());
        }
        return null;
    }

    /**
     * gson 转json 为object
     */
    private static <T> T gsonCast(String json, Class<T> c) {
        try {
            return GSON.fromJson(json, c);
        } catch (Exception e) {
            System.out.println("gsonCast parseJsonToObj error " + e.getMessage());
        }
        return null;
    }

    /**
     * jackson 转json 为object
     */
    private static <T> T jacksonCast(String json, Class<T> c) {
        try {
            return OBJECT_MAPPER.readValue(json, c);
        } catch (Exception e) {
            System.out.println("jacksonCast parseJsonToObj error " + e.getMessage());
        }
        return null;
    }

    private static <T> List<T> fastjsonCastArray(String json, Class<T> c) {
        try {
            return JSON.parseArray(json, c);
        } catch (Exception e) {
            System.out.println("fastjsonCastArray parseJsonToObj error " + e.getMessage());
        }
        return null;
    }

    private static <T> List<T> jacksonCastArray(String json, Class<T> c) {
        try {
            return OBJECT_MAPPER.readValue(json, OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, c));
        } catch (Exception e) {
            System.out.println("jacksonCastArray parseJsonToObj error " + e.getMessage());
        }
        return null;
    }

}
