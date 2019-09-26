package com.lxk.jdk.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式测试
 *
 * @author LiXuekai on 2019/9/25
 */
public class RegexTest {

    /**
     *
     */
    private static Pattern EXISTS_PATTERN = Pattern.compile("[^\\\\]\"");
    /**
     *
     */
    private static Pattern REPLACE_PATTERN = Pattern.compile("[^\\\\]\"(([^\"])|(\\\\\"))*[^\\\\]\"");
    /**
     *
     */
    private static Pattern RANGE_PATTERN = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
    /**
     * * 任意字符，，，包含这个的字符串都行能匹配上
     */
    private Pattern pattern = Pattern.compile("123*456");


    @Test
    public void test() {
        Matcher matcher = pattern.matcher("sadasdasd/1233456/dasdad");

        boolean matches = matcher.matches();
        //false
        System.out.println(matches);

        boolean b = matcher.find();
        //true
        System.out.println(b);


    }
}
