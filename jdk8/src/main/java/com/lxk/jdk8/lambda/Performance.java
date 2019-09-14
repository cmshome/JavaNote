package com.lxk.jdk8.lambda;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 测试性能(Java 8 的循环和Java 7 的循环耗时的对比测试)
 * <p>
 * @author lxk on 2017/8/31
 */
public class Performance {
    public static void main(String[] args) {
        List<String> list = getLoopList();
        normalBeforeLoop(list);
        normalAfterLoop(list);
        notNormalAfterLoop(list);
    }

    private static void notNormalAfterLoop(List<String> list) {
        long a = System.currentTimeMillis();
        list.stream().parallel().forEach(System.out::print);
        System.out.println(" list.stream().parallel().forEach 执行耗时 : " + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
    }

    private static void normalAfterLoop(List<String> list) {
        long a = System.currentTimeMillis();
        list.stream().forEach(System.out::print);
        System.out.println(" list.stream().forEach 执行耗时 : " + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
        a = System.currentTimeMillis();
        list.forEach(System.out::print);
        System.out.println(" list.forEach 执行耗时 : " + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
    }

    private static void normalBeforeLoop(List<String> list) {
        long a = System.currentTimeMillis();
        for (String s : list) {
            System.out.print(s);
        }
        System.out.println(" for each 执行耗时 : " + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
    }

    private static List<String> getLoopList() {
        List<String> list = Lists.newArrayList();
        for (int i = 0; i < 10000; i++) {
            list.add("item " + i);
        }
        return list;
    }
}
