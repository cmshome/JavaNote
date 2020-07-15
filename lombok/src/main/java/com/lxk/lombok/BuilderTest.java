package com.lxk.lombok;


import com.lxk.bean.extend.TCL;
import com.lxk.bean.model.Bird;
import com.lxk.bean.model.Dog;
import com.lxk.bean.model.Phone;
import com.lxk.tool.JsonUtils;
import org.junit.Test;

import java.util.Date;

/**
 * 测试@Builder
 *
 * @author LiXuekai on 2019/5/13
 */
public class BuilderTest {
    public static void main(String[] args) {
        //testConstructor();
        //testDefaultValue();
        //testUpdateField();
        //testExtend();
        test();
    }

    private static void test() {
        //boolean 默认都是false
        System.out.println(JsonUtils.parseObjToJson(Dog.builder().build()));
        //改了一个true
        Dog dog = Dog.builder().isLoyal(true).name("000").build();
        System.out.println(JsonUtils.parseObjToJson(dog));
        //新new一个dog
        Dog xxx = dog.toBuilder().isLoyal(false).alive(true).build();
        System.out.println(JsonUtils.parseObjToJson(xxx));
    }

    /**
     * 测试继承
     */
    private static void testExtend() {
        TCL tcl1 = TCL.builder().build();
        System.out.println(tcl1);
    }

    /**
     * 测试修改model的属性值
     */
    private static void testUpdateField() {
        Bird build = Bird.builder().dog1("12").dog2("34").birthday(new Date())
                .color("红色皮肤").size("巨大无比").age(18)
                .name("典韦").deserialize("ssss").build();
        System.out.println(JsonUtils.parseObjToJson(build));
        Bird after = build.toBuilder().name("大哥").build();
        System.out.println(JsonUtils.parseObjToJson(build));
        System.out.println(JsonUtils.parseObjToJson(after));
    }

    /**
     * 测试默认值
     */
    private static void testDefaultValue() {
        Bird s = Bird.builder().build();
        System.out.println(JsonUtils.parseObjToJson(s));
        Bird ss = new Bird();
        System.out.println(JsonUtils.parseObjToJson(ss));
    }

    /**
     * 测试构造
     */
    private static void testConstructor() {
        Bird bird = new Bird("12", "34", new Date(), "红色皮肤", "巨大无比", 18, "典韦", "不序列化的字段，是不会被转json输出的");
        Bird build = Bird.builder().dog1("12").dog2("34").birthday(new Date())
                .color("红色皮肤").size("巨大无比").age(18)
                .name("典韦").deserialize("不序列化的字段，是不会被转json输出的").build();
        System.out.println(build.toString());
        System.out.println(bird.toString());

        System.out.println(bird.getName());
        bird.toBuilder().name("lxk").build();
        System.out.println(bird.getName());
    }

    /**
     * 当使用@Builder的时候，原来的无参数构造函数会被覆盖掉，默认出了个全参数的构造函数。
     */
    @Test
    public void test2() {
        Phone phone = Phone.builder().build();
    }

}
