package com.lxk.jdk.constant;


import static com.lxk.jdk.constant.Constants.CommonConstant.*;
import static com.lxk.jdk.constant.Constants.Metrics.*;
import static com.lxk.jdk.constant.Constants.Numbers.*;

/**
 * 常量的测试(主要是想表达常量也是可以分类的)
 *
 * @author LiXuekai on 2019/8/13
 */
public class ConstantsTest {
    public static void main(String[] args) {
        testCommon();
        testMetrics();
        testNumbers();
    }

    private static void testNumbers() {
        System.out.println(SPLIT);
        System.out.println(ZERO);
        System.out.println(ONE);
        System.out.println(TWO);
        System.out.println(THREE);
        System.out.println(FOUR);
        System.out.println(FIVE);
    }

    private static void testMetrics() {
        System.out.println(IP);
        System.out.println(PORT);
        System.out.println(SPLIT);
    }

    private static void testCommon() {
        System.out.println(SPLIT);
    }
}
