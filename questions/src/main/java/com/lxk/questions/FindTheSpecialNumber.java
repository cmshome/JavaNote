package com.lxk.questions;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author lxk on 2016/10/27
 *
 * 题目扩展：一个无序数组里有若干个正整数，范围从1到100，其中99个整数都出现了偶数次，
 * 只有一个整数出现了奇数次（比如1,1,2,2,3,3,4,5,5），如何找到这个出现奇数次的整数？
 *
 * 题目第二次扩展：一个无序数组里有若干个正整数，范围从1到100，其中98个整数都出现了偶数次，
 * 只有两个整数出现了奇数次（比如1,1,2,2,3,4,5,5），如何找到这个出现奇数次的整数？
 */
public class FindTheSpecialNumber {
    public static void main(String[] args) {
        Integer a[] = setArray();
        Integer b[] = setArray();
        Integer result[] = getCombinedIntArray(a, b);
        result[198] = 100;
        test(result);
        //再次测试，以证明程序OK。
        Integer[]  ss = {3,1,1,2,2,3,4,5,5,6,7};
        test(ss);
    }

    /**
     * 合并2个数组为一个数组 长度是99 * 2 + 1 = 199
     * 1-99存了2遍 + 100
     * Java jdk直接合并数组的方法。(注释的是简单数组copy实现)
     */
    private static Integer[] getCombinedIntArray(Integer[] a, Integer[] b) {
        Integer result[] = new Integer[199];
        //System.arraycopy(a, 0, result, 0, 99);
        //Parameters:参数解释 java.lang 包里面的
        //src - the source array.
        //srcPos - starting position in the source array.
        //dest - the destination array.
        //destPos - starting position in the destination data.
        //length - the number of array elements to be copied.
        System.arraycopy(a, 0, result, 0, 99);
        System.arraycopy(b, 0, result, 99, 99);
        //for (int i = 99; i < 198; i++) {
        //    result[i] = b[i - 99];
        //}
        return result;
    }

    /**
     * 获得一个1-99的数组 长度99
     * [1,2,...99]
     */
    private static Integer[] setArray() {
        Integer a[] = new Integer[99];
        for (int i = 0; i < 99; i++) {
            a[i] = i + 1;
        }
        return a;
    }

    private static void test(Integer[] array) {
        Map<Integer, Integer> map = Maps.newHashMapWithExpectedSize(100);
        List<Integer> list = Lists.newArrayList(Arrays.asList(array));
        Collections.sort(list);
        int size = list.size();
        int n = 1;
        for (int i = 0; i < size; i++) {
            if (i < (size - 1) && list.get(i).equals(list.get(i + 1))) {
                n++;
            } else {
                map.put(list.get(i), n);
                n = 1;
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if ((entry.getValue() % 2) == 1) {//模2 是奇数的map的key就是结果
                System.out.println(entry.getKey());
            }
        }
        //循环输出map的key和其对应的value
        //for (Integer integer : map.keySet()) {
        //    System.out.println("key " + (integer < 10 ? ("0" + integer) : integer) + " value " + map.get(integer));
        //}
        //System.out.println("map.size() " + map.size());
    }
}
