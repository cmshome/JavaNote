package com.lxk.jdk.common;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Random;

/**
 * 常用方法测试
 *
 * @author lxk on 2017/7/14
 */
public class MathTest {

    @Test
    public void testSpecial() {
        int a = 7;
        // >>：带符号右移。正数右移高位补0，负数右移高位补1。比如：4 >> 1，结果是2；-4 >> 1，结果是-2。-2 >> 1，结果是-1。
        // >>>：无符号右移。无论是正数还是负数，高位通通补0。
        //对于正数而言，>>和>>>没区别。
        //对于负数而言，-2 >>> 1，结果是2147483647（Integer.MAX_VALUE），-1 >>> 1，结果是2147483647（Integer.MAX_VALUE）。
        System.out.println(Integer.toBinaryString(a));
        a = a << 1;
        System.out.println("a << 1：" + a);
        System.out.println(Integer.toBinaryString(a));
        System.out.println("a >>> 1：" + (a >>> 1));
    }

    /**
     * 四舍五入，向上向下取整。
     */
    @Test
    public void testCeilFloor() {
        //13.0
        System.out.println(Math.ceil(12.4));
        //12.0
        System.out.println(Math.floor(12.4));
        //12
        System.out.println(Math.round(12.4));
        //13
        System.out.println(Math.round(12.5));
    }

    /**
     * 开方，注意，返回是double。
     */
    @Test
    public void testSqrt() {
        //4.0
        System.out.println(Math.sqrt(16));
    }

    /**
     * 绝对值
     */
    @Test
    public void testAbs() {
        //int, long, float, double
        //1
        long aa = -100;
        System.out.println(Math.abs(aa));
    }

    /**
     * 平方，注意，返回是double。
     */
    @Test
    public void testPow() {
        //结果是：1000.0
        System.out.println(Math.pow(10, 3));
    }

    /**
     * 随机生成小数
     */
    @Test
    public void testRandomFloat() {
        Random rand = new Random();
        Double aa = (Math.pow(0.1, 4));
        BigDecimal b = new BigDecimal(aa.toString());
        //取四位，四舍五入。
        aa = b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        //aa:1.0E-4     输出是科学计数法
        System.out.println("aa:" + aa);
        System.out.println("aa:" + b);
    }



}
