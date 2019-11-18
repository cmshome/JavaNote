package com.lxk.jdk.collection;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author lxk on 2017/4/11
 */
public class HashMapTest {
    public static void main(String[] args) {
        //testIterator();
        putAllEmpty();
    }

    private static void putAllEmpty() {
        Map<String, String> map = Maps.newHashMap();
        HashMap<String, String> maps = Maps.newHashMap();

        map.putAll(maps);
        System.out.println(map);

    }

    /**
     * 测试Iterator迭代器
     */
    private static void testIterator() {
        HashMap<String, String> map = Maps.newHashMap();
        map.put("a", "one");
        map.put("b", "two");
        map.put("c", "three");
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 测试remove一个不存在的key，会发生什么情况。结果：正常运行，无异常。
     */
    @Test
    public void removeTest(){
        HashMap<String, String> map = Maps.newHashMap();
        map.put("a", "one");
        map.put("b", "two");
        map.put("c", "three");

        map.remove("sss");
        System.out.println(map.size());
    }
}
