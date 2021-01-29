package com.lxk.tool;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * 自定义集合类
 *
 * @author lxk on 2017/11/13
 */
public class CollectionUtil {
    private static final int DEFAULT_SIZE = 5;

    /**
     * 获得底层是数组的list集合
     */
    public static List<String> getArrayList(Integer size) {
        size = (size == null || size <= 0) ? DEFAULT_SIZE : size;
        List<String> list = Lists.newArrayListWithExpectedSize(size);
        for (int i = 0; i < size; i++) {
            list.add(i + "1234567890");
        }
        return list;
    }

    /**
     * 获得底层是数组的数组
     */
    public static String[] getArray(Integer size) {
        size = (size == null || size <= 0) ? DEFAULT_SIZE : size;
        String[] array = new String[size];
        for (int i = 0; i < size; i++) {
            array[i] = (i + "1234567890");
        }
        return array;
    }

    /**
     * 获得底层是链表的list集合
     */
    public static List<String> getLinkedList(Integer size) {
        size = (size == null || size <= 0) ? DEFAULT_SIZE : size;
        List<String> list = Lists.newLinkedList();
        for (int i = 0; i < size; i++) {
            list.add(i + "1234567890");
        }
        return list;
    }

    /**
     * 获得底层是链表的list集合
     */
    public static Map<String, String> getMap(Integer size) {
        size = (size == null || size <= 0) ? DEFAULT_SIZE : size;
        Map<String, String> map = Maps.newHashMapWithExpectedSize(size);
        for (int i = 0; i < size; i++) {
            map.put(Integer.toString(i), Integer.toString(i));
        }
        return map;
    }
}
