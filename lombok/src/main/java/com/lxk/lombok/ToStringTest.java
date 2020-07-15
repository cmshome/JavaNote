package com.lxk.lombok;

import com.lxk.bean.model.Bird;
import com.lxk.bean.model.Dog;
import com.lxk.tool.JsonUtils;
import org.junit.Test;

import java.util.Date;

/**
 * toString中属性名称是可以替换字符串输出的
 *
 * @author LiXuekai on 2019/12/31
 */
public class ToStringTest {

    @Test
    public void test() {
        String dog1 = JsonUtils.parseObjToJson(new Dog("哈士奇", true, true));
        String dog2 = JsonUtils.parseObjToJson(new Dog("柴犬", true, true));

        Bird bird = Bird.builder()
                .name("妮露")
                .age(500)
                .birthday(new Date())
                .color("black and red")
                .deserialize("不序列号字段的属性的值")
                .size("不大不小")
                .dog1(dog1)
                .dog2(dog2)
                .build();
        System.out.println(bird.toString());
        System.out.println();
        System.out.println(JsonUtils.parseObjToFormatJson(bird));
        //{
        //    "名称":"妮露",
        //        "年龄":500,
        //        "体型大小":"不大不小",
        //        "颜色":"black and red",
        //        "生产日期":"2020年01月03日 14时38分42秒",
        //        "dog2属性":"{\"年纪\":\"柴犬\",\"忠诚\":true,\"存活\":true}",
        //        "dog1属性":{
        //              "忠诚":true,
        //              "存活":true,
        //              "年纪":"哈士奇"
        //          }
        //}
    }
}
