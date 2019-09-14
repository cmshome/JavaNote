package com.lxk.jdk.fast;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 对map操作对时候，当key越简单，则存取效率是不是会相应的跟着提高呢？
 * 确实
 *
 * @author LiXuekai on 2019/6/21
 */
public class MapKeyIsEasyIsFast {
    private static final int MAX = 100000;

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        while (true) {
            //25%
            intKey();
            //43%
            complex();
            //31%
            simple();
        }
    }

    private static void intKey() {
        Map<Integer, String> map = Maps.newHashMap();
        for (Integer i = 0; i < MAX; i++) {
            map.put(i, i + "li xue kai");
        }
    }

    private static void simple() {
        Map<String, String> map = Maps.newHashMap();
        for (Integer i = 0; i < MAX; i++) {
            map.put(i.toString(), i + "li xue kai");
        }
    }

    private static void complex() {
        Map<String, String> map = Maps.newHashMap();
        for (Integer i = 0; i < MAX; i++) {
            map.put(i + "li xue kai more complex", i + "li xue kai");
        }
    }
}
