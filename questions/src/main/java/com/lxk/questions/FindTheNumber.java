package com.lxk.questions;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 一个无序数组里有99个不重复正整数，范围从1到100，唯独缺少一个整数。如何找出这个缺失的整数？
 *
 * @author lxk on 2016/10/27
 */
public class FindTheNumber {
    public static void main(String[] args) {
        int a[] = setArray();
        int result = test1(a);
        System.out.println(result);
        //Integer a= 2;
        //System.out.println(a / 2);
    }

    /**
     * 获得一个1-99的数组,缺失100这个整数 长度99
     * [1,2,...99]
     */
    private static int[] setArray() {
        int a[] = new int[99];
        for (int i = 0; i < 99; i++) {
            a[i] = i + 1;
        }
        return a;
    }

    /**
     * 处理逻辑：筛选出那个缺失的整数
     */
    private static int test1(int[] a) {
        Set<Integer> all = Sets.newHashSetWithExpectedSize(100);
        for (int i : a) {
            all.add(i);
        }
        for (int i = 1; i < 101; i++) {
            if (all.add(i)) {
                return i;
            }
        }
        return 0;//返回0就失败
    }
}
