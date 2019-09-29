package com.lxk.controller;

import com.lxk.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

/**
 * 测试配置文件的使用情况
 * <p>
 * Created by lxk on 2017/5/23
 */
public class DefaultSettingTest extends AbstractTest {

    @Value("${name:LXK}")
    private String name;

    @Value("${age:27}")
    private int age;

    @Value("${sex:man}")
    private String sex;

    @Test
    public void outDefaultValue() {
        System.out.println();
        System.out.println(name);
        System.out.println(age);
        System.out.println(sex);
    }

}
