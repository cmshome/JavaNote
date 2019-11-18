package com.lxk.jdk.common;

import org.junit.Test;

import java.util.Objects;

/**
 * hash code
 *
 * @author LiXuekai on 2019/11/6
 */
public class HashCodeTest {

    @Test
    public void hashcode() {
        int i = com.google.common.base.Objects.hashCode("12313123");
        //-1864188419
        System.out.println(i);
        //-1864188419
        System.out.println(Objects.hash("12313123"));
        //-1864188419
        System.out.println(Objects.hash("12313123"));
        //-1864188450
        System.out.println(Objects.hashCode("12313123"));

        //0
        System.out.println(Objects.hash(null));
        //31
        System.out.println(Objects.hash(""));

        System.out.println(Objects.hash(new StringBuilder().toString()));

    }
}
