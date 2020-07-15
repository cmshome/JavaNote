package com.lxk.json.fastjson.jsonfield;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lxk.bean.model.*;
import com.lxk.tool.JsonUtils;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * 测试 fast json 之@JSONField 注解
 *
 * @author LiXuekai on 2019/9/16
 */
public class JsonFieldTest {


    /**
     * 测试json的有序 【@JSONField(ordinal = 1)】
     */
    @Test
    public void testOrdered() {
        House house = new House(180, 180, "lxk", 180);
        //如果不使用注解（），不排序，json字符串的属性按字母顺序排序，不按model里面属性的顺序。
        System.out.println(JSON.toJSONString(house));
    }

    /**
     * 测试 fast json 中的JsonField注解的使用
     * 测试 @JSONField(ordinal = 4, name = "生产日期", format = "yyyy年MM月dd日 HH时mm分ss秒")
     * 测试 @JSONField(ordinal = 6, name = "dog1属性", jsonDirect = true)
     * 测试 @JSONField(name = "名称", deserialize = false)
     * 测试 @JSONField(ordinal = 7, name = "不序列化的属性字段", serialize = false)
     * 看连接 https://blog.csdn.net/qq_27093465/article/details/83381091
     */
    @Test
    public void testJsonField() {
        String dog = JsonUtils.parseObjToJson(new Dog("大师兄的dog", true, true));
        Bird bird = new Bird(dog, dog, new Date(), "红色皮肤", "巨大无比", 18, "典韦", "不序列化的字段，是不会被转json输出的");
        System.out.println(bird.toString());
        String birdJson = JsonUtils.parseObjToFormatJson(bird);
        System.out.println(birdJson);

        System.out.println();

        //key已经被name修改了，所以，在发序列化的时候就炸了。
        Bird deserialize = JsonUtils.parseJsonToObj(birdJson, Bird.class);
        System.out.println(deserialize == null ? "" : deserialize.toString());
    }

    /**
     * 对象转json，json转对象。
     */
    @Test
    public void obj2Json() {
        Student student = getStudent();
        String studentJson = JsonUtils.parseObjToFormatJson(student);
        System.out.println(studentJson);

        Student studentFromJson = JsonUtils.parseJsonToObj(studentJson, Student.class);
        System.out.println(studentFromJson);
    }

    /**
     * 获得测试对象
     */
    private static Student getStudent() {
        Dog dog1 = new Dog("大师兄的dog1", true, true);
        Dog dog2 = new Dog("大师兄的dog2", false, false);
        List<Dog> dogs = Lists.newArrayList();
        dogs.add(dog1);
        dogs.add(dog2);
        List<String> boys = Lists.newArrayList("tom", "jerry", "jack");
        Car car = new Car("q7", 182, dogs, boys);
        Student student = new Student();
        student.setName("Lxk");
        student.setCar(car);
        return student;
    }
}
