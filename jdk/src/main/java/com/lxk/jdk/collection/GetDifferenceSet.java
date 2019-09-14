package com.lxk.jdk.collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 取两个集合的差集
 * <p>
 * @author lxk on 2017/2/17
 */
public class GetDifferenceSet {
    public static void main(String[] args) {
        List<String> big = Lists.newArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        List<String> small = Lists.newArrayList("1", "2", "3", "3", "2", "1");
        long a=System.currentTimeMillis();
        List<String> guava = getDifferenceSetByGuava(big, small);
        System.out.println("\r<br> 执行耗时 : "+(System.currentTimeMillis()-a)/1000f+" 秒 ");
        //为了显示一致，我给集合排个序，因为guava是按newHashSet集合来整的，newHashSet又是无序的,so ...
        Collections.sort(guava);
        a=System.currentTimeMillis();
        List<String> my = getDifferenceSetByMyself(big, small);
        System.out.println("\r<br> 执行耗时 : "+(System.currentTimeMillis()-a)/1000f+" 秒 ");
        System.out.println(guava.toString());
        System.out.println(my.toString());

        Map<String, String> bigMap = Maps.newHashMap();
        bigMap.put("1", "1");
        bigMap.put("2", "2");
        bigMap.put("3", "3");
        bigMap.put("4", "4");
        Map<String, String> smallMap = Maps.newHashMap();
        smallMap.put("1", "1");
        smallMap.put("2", "2");
        a=System.currentTimeMillis();
        Map<String, String> guavaMap = getDifferenceSetByGuava(bigMap, smallMap);
        System.out.println("\r<br> 执行耗时 : "+(System.currentTimeMillis()-a)/1000f+" 秒 ");
        System.out.println(guavaMap);
    }

    /**
     * 使用guava工具类来取List集合的差集--专业轮子谷歌造
     *
     * @param big   大集合
     * @param small 小集合
     * @return 两个集合的差集
     */
    private static List<String> getDifferenceSetByGuava(List<String> big, List<String> small) {
        Set<String> differenceSet = Sets.difference(Sets.newHashSet(big), Sets.newHashSet(small));
        return Lists.newArrayList(differenceSet);
    }

    /**
     * 自己实现取List集合的差集--自制轮子大师兄造
     *
     * @param big   大集合
     * @param small 小集合
     * @return 两个集合的差集
     */
    private static List<String> getDifferenceSetByMyself(List<String> big, List<String> small) {
        Set<String> sameString = Sets.newHashSet();
        for (String s : small) {
            sameString.add(s);
        }
        List<String> result = Lists.newArrayList();
        for (String s : big) {
            if (sameString.add(s)) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * 自己实现取Map集合的差集--站在巨人的肩膀上造轮子
     *
     * @param bigMap   大集合
     * @param smallMap 小集合
     * @return 两个集合的差集
     */
    private static Map<String, String> getDifferenceSetByGuava(Map<String, String> bigMap, Map<String, String> smallMap) {
        Set<String> bigMapKey = bigMap.keySet();
        Set<String> smallMapKey = smallMap.keySet();
        Set<String> differenceSet = Sets.difference(bigMapKey, smallMapKey);
        Map<String, String> result = Maps.newHashMap();
        for (String key : differenceSet) {
            result.put(key, bigMap.get(key));
        }
        return result;
    }
}
