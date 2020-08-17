package com.lxk.jdk.math;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author LiXuekai on 2020/8/17
 */
public class BigDecimalTest {
    @Test
    public void test() {
        double num = 11.3456723;
        System.out.println("数字构造器" + new BigDecimal(Double.valueOf(num)));
        System.out.println("字符构造器" + new BigDecimal(Double.valueOf(num).toString()));
        System.out.println(BigDecimal.valueOf(num));
    }
}
