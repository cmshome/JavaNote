package com.lxk.jdk.collection;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 测试Map是否有序的区别（hashMap，treeMap，linkedHashMap）
 * 结论：
 *      hashMap，         无序。
 *      treeMap，         有序，但跟添加顺序无关。
 *      linkedHashMap，   有序，且跟添加顺序一致。
 * <p>
 * @author lxk on 2017/5/24
 */
public class OrderedMapTest {
    public static void main(String[] args) {
        Map<String, Integer> hashMap = Maps.newHashMap();
        Map<String, Integer> treeMap = Maps.newTreeMap();
        Map<String, Integer> linkedHashMap = Maps.newLinkedHashMap();
        System.out.println("--------------test hashMap");
        testMap(hashMap);
        System.out.println("--------------test treeMap");
        testMap(treeMap);
        System.out.println("--------------test linkedHashMap");
        testMap(linkedHashMap);
    }

    private static void testMap(Map<String, Integer> map) {
        map.put("asd", 1);
        map.put("2das", 2);
        map.put("3das", 3);
        map.put("4das", 4);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

}
