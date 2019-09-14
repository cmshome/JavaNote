package com.lxk.jdk8.lambda;

import com.google.common.collect.Lists;
import com.lxk.tool.collection.CollectionUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 根据条件，删除集合中的元素
 *
 * @author lxk on 2018/1/24
 */
public class RemoveIfTest {
    public static void main(String[] args) {
        //removeIfTest();
        //beforeRemove();
        mapRemoveIf();
    }

    private static void mapRemoveIf() {
        Map<String, String> map = CollectionUtil.getMap(4);
        System.out.println(map.toString());
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            String value = map.get(key);
            if (key.equals("1")){
                iterator.remove();
            }
        }
        System.out.println(map.toString());
    }

    /**
     * 咱之前是怎么从集合中过滤掉不要的元素的
     */
    private static void beforeRemove() {
        List<String> list = Lists.newArrayList("1", "12", "13", "14", "15", "0");
        System.out.println("初始时：" + list.toString());

        System.out.println("for i 循环删除元素");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains("1")) {
                list.remove(i);
                //一般之前这么操作集合的时候，估计都是会忘记 i-- 的
                i--;
            }
        }
        System.out.println("过滤完：" + list.toString());

        System.out.println("Iterator 迭代器 循环删除元素");
        list = Lists.newArrayList("1", "12", "13", "14", "15", "0");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().contains("1")) {
                iterator.remove();
            }
        }
        System.out.println("过滤完：" + list.toString());
        System.out.println("加强 for 循环  循环删除元素，直接异常。");
        list = Lists.newArrayList("1", "12", "13", "14", "15", "0");
        for (String s : list) {
            if (s.contains("1")) {
                list.remove(s);
            }
        }
        System.out.println("过滤完：" + list.toString());
    }

    /**
     * 删除集合中符合条件的成员，empty集合也可以，但是null就炸啦。
     */
    private static void removeIfTest() {
        List<String> list = Lists.newArrayList("1", "12", "13", "14", "15", "0");
        System.out.println("初始时：" + list.toString());
        list.removeIf(s -> s.contains("1"));
        System.out.println("过滤完：" + list.toString());

        list.forEach(System.out::println);
    }
}
