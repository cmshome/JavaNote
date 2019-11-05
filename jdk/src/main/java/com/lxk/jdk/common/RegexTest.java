package com.lxk.jdk.common;

import org.junit.Test;

/**
 * 正则表达式测试
 *
 * @author LiXuekai on 2019/11/5
 */
public class RegexTest {

    @Test
    public void regex() {
        String regex = "(\\d{4}).*(\\d{4})";
        String text = "13811229209";
        String replaceAll = text.replaceAll(regex, "$1 **** **** $2");
        System.out.println(replaceAll);
    }

}
