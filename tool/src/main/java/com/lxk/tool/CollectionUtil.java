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
    private static final int SIZE = 5;

    /**
     * 获得底层是数组的list集合
     */
    public static List<String> getArrayList(Integer size) {
        size = (size == null || size <= 0) ? SIZE : size;
        List<String> list = Lists.newArrayListWithExpectedSize(size);
        for (Integer i = 0; i < size; i++) {
            list.add(i.toString() + "1234567890");
        }
        return list;
    }

    /**
     * 获得底层是数组的数组
     */
    public static String[] getArray(Integer size) {
        size = (size == null || size <= 0) ? SIZE : size;
        String[] list = new String[size];
        for (Integer i = 0; i < size; i++) {
            list[i] = (i.toString() + "1234567890");
        }
        return list;
    }

    /**
     * 获得底层是链表的list集合
     */
    public static List<String> getLinkedList(Integer size) {
        size = (size == null || size <= 0) ? SIZE : size;
        List<String> list = Lists.newLinkedList();
        for (Integer i = 0; i < size; i++) {
            list.add(i.toString() + "1234567890");
        }
        return list;
    }

    /**
     * 获得底层是链表的list集合
     */
    public static Map<String, String> getMap(Integer size) {
        size = (size == null || size <= 0) ? SIZE : size;
        Map<String, String> map = Maps.newHashMapWithExpectedSize(size);
        for (Integer i = 0; i < size; i++) {
            map.put(i.toString(), i.toString());
        }
        return map;
    }
}
