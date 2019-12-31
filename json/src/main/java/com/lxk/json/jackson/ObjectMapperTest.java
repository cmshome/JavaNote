package com.lxk.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lxk.bean.model.Dog;
import com.lxk.tool.json.JsonUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * jackson 中的
 *
 * @author LiXuekai on 2019/12/29
 */
public class ObjectMapperTest {

    /**
     * ObjectMapper json 转对象的时候，
     * json比model的属性多，就异常的问题
     */
    @Test
    public void jackson() {
        ObjectMapper om = new ObjectMapper();
        String json = getDogJson();
        System.out.println(json);

        json = "{\"alive\":true,\"loyal\":true,\"name\":\"tom\",\"color\":\"black\"}";
        //json = "{\"alive\":true,\"loyal\":true}";
        try {
            Dog readValue = om.readValue(json, Dog.class);
            System.out.println(readValue);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 就算json字符串中多了key value，model bean没有的，他就自动忽视啦。
     */
    @Test
    public void fastjson() {
        String json = "{\"alive\":true,\"loyal\":true,\"name\":\"tom\",\"color\":\"black\"}";
        Dog dog = JsonUtils.parseJsonToObj(json, Dog.class);
        System.out.println(dog);
    }

    /**
     * @return {"alive":true,"loyal":true,"name":"tom"}
     */
    private String getDogJson() {
        Dog dog = Dog.builder().alive(true).isLoyal(true).name("tom").build();
        return JsonUtils.parseObjToJson(dog);
    }
}
