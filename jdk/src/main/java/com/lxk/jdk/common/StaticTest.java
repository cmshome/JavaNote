package com.lxk.jdk.common;

/**
 * @author lxk on 2018/7/25
 */
public class StaticTest {
    private static String s = "sss";

    public static void main(String[] args) {
        s = "yyyy";
        System.out.println(s);

    }
}
