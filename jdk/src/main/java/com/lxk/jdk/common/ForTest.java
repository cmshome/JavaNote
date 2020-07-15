package com.lxk.jdk.common;


import com.google.common.collect.Lists;
import com.lxk.tool.CollectionUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 关于for循环的测试
 *
 * @author lxk on 2017/4/21
 */
public class ForTest {
    private static final int SIZE = 40000;


    /**
     * 测试下for each写法中的循环体，是否是只执行一次。测试结果，是的。
     */
    @Test
    public void forTest(){
        for (String s : getList()) {
            System.out.println(s);
        }
    }

    private ArrayList<String> getList() {
        System.out.println("get list run...");
        return Lists.newArrayList("1","2","3","4");
    }

    /**
     * 测试不同for的效率问题
     */
    @Test
    public void testForEfficiency() {
        String[] array = CollectionUtil.getArray(SIZE);
        List<String> arrayList = CollectionUtil.getArrayList(SIZE);
        List<String> linkedList = CollectionUtil.getLinkedList(SIZE);

        while (true) {
            //array 0.5% 0.5%
            testArray(array);
            //for each : for i : lambda = 1.2% : 0.8% : 0.9%
            testArrayList(arrayList);
            //for each : for i : lambda = 1.2% : 94% : 0.9%
            testLinkedList(linkedList);
        }

    }

    /**
     * array 在使用forI循环的效率测试
     * 对数组来说，for i 和for each 效率是一样的
     */
    private static void testArray(String[] array) {
        testForI(array);
        testForEach(array);
    }

    /**
     * arrayList 在使用forI循环，forEach循环和lambda循环的效率测试
     */
    private static void testArrayList(List<String> arrayList) {
        testForI(arrayList);
        testForEach(arrayList);
        testLambda(arrayList);
    }

    /**
     * linkedList 在使用forI循环，forEach循环和lambda循环的效率测试
     */
    private static void testLinkedList(List<String> linkedList) {
        testForI(linkedList);
        testForEach(linkedList);
        testLambda(linkedList);
    }

    private static void testForI(String[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            sb.append(array[i]);
        }
    }

    private static void testForEach(String[] array) {
        StringBuilder sb = new StringBuilder();
        for (String s : array) {
            sb.append(s);
        }
    }
    private static void testForI(List<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            sb.append(arrayList.get(i));
        }
    }

    private static void testForEach(List<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        for (String s : arrayList) {
            sb.append(s);
        }
    }

    private static void testLambda(List<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        arrayList.forEach(sb::append);
    }

    private static void forIAfter() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            sb.append(i);
        }
    }

    private static void forIBefore() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; ++i) {
            sb.append(i);
        }
    }

    /**
     * 前后使用++对效率没影响，都是百分之五十的比例。
     */
    @Test
    public void test() {
        while (true) {
            forIAfter();
            forIBefore();
        }
    }
}
