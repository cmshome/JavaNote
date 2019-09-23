package com.lxk.jdk.collection;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * List 相关方法的测试
 *
 * @author lxk on 2017/3/13
 */
public class ListTest {
    public static void main(String[] args) {

    }

    /**
     * ConcurrentModificationException 异常的三种常见情况
     */
    private static void concurrentModificationException() {

    }

    /**
     * 左闭右开
     */
    @Test
    public void subListTest() {
        List<String> list = Lists.newArrayList("0", "1", "2", "3", "4", "5", "6");
        System.out.println(list.toString());
        List<String> subList = list.subList(0, 5);
        System.out.println(subList.toString());
    }
}
