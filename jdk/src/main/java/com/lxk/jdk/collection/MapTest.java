package com.lxk.jdk.collection;

import com.google.common.collect.Maps;
import com.lxk.tool.collection.CollectionUtil;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

/**
 * map test
 *
 * @author LiXuekai on 2020/1/4
 */
public class MapTest {

    /**
     * put return old value
     */
    @Test
    public void putTest() {
        Map<String, String> map = Maps.newHashMap();
        String put = map.put("ss", "yy");
        System.out.println(put);
        put = map.put("ss", "zz");
        System.out.println(put);
    }

    /**
     * 在遍历的时候删除元素会出现下面的异常
     * java.util.ConcurrentModificationException
     */
    @Test
    public void remove() {

        Map<String, String> map = CollectionUtil.getMap(5);
        System.out.println(map.toString());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().equals("1")) {
                map.remove("1");
            }
        }
        System.out.println(map.toString());
    }

    /**
     * hashmap没有 list似的有个  removeIf
     */
    @Test
    public void removeIf() {
        Map<String, String> map = CollectionUtil.getMap(5);
        System.out.println(map.toString());
    }

    /**
     * 使用迭代器，在循环map的时候删除某个元素，线程安全。
     */
    @Test
    public void iteratorRemove() {
        Map<String, String> map = CollectionUtil.getMap(5);
        System.out.println(map.toString());
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = map.get(key);
            if ("1".equals(value)) {
                iterator.remove();
            }
        }
        System.out.println(map.toString());
    }
}
