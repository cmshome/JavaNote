package com.lxk.jdk.random;


import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Random;
import java.util.Set;

/**
 * @author lxk on 2018/5/3
 */
public class RandomTest {

    /**
     * 使用随机算法产生一个数，要求把1-1000W之间这些数全部生成。
     */
    @Test
    public void createRandom4() {
        Random random = new Random();
        long start = System.currentTimeMillis();
        int value = 10000000;
        //使用数组速度更快，不到一秒。
        int[] list = new int[value];
        //十几秒
        //Integer[] list = new Integer[value];

        for (int j = 1; j <= value; ++j) {
            list[j - 1] = j;
        }

        int index = 0;
        int count = 0;
        int tmp = 0;
        while (value > 0) {
            index = random.nextInt(value);
            tmp = list[count + index];
            list[count + index] = list[count];
            list[count] = tmp;
            ++count;
            --value;
        }

        long end = System.currentTimeMillis();
        //输出扰乱之后的集合
        //for (Integer integer : list) {
        //    System.out.print(integer + " ");
        //}
        //System.out.println();
        //----验证是否正确
        //前提是使用Integer对象数组，而不是int数组。
        //List<Integer> res = Lists.newArrayList(Arrays.asList(list));
        //Collections.sort(res);
        //for (int i = 0; i < res.size(); ++i) {
        //    //System.out.print(res.get(i) + " ");
        //    if (res.get(i) != (i + 1)) {
        //        System.out.println(i + "error" + res.get(i));
        //    }
        //}
        //----验证是否正确
        //System.out.println();
        System.out.println("create4------");
        System.out.println("执行耗时 : " + (end - start) / 1000f + " 秒 ");
        System.out.println("完了，集合大小为" + list.length);
    }

    /**
     * 使用随机算法产生一个数，要求把1-1000W之间这些数全部生成。
     * （考察高效率，解决产生冲突的问题）
     */
    @Test
    public void testRandom() {
        int value = 10000000;
        //int类型最大值：2的32次方 - 1 = Integer.MAX_VALUE = 2147483647，二十亿多,真够啦 。
        Set<Integer> result = Sets.newHashSetWithExpectedSize(value);
        Random random = new Random();
        long a = System.currentTimeMillis();
        while (result.size() < value + 1) {
            int i = random.nextInt(value + 1);
            result.add(i);
        }

        System.out.println("\r<br> 执行耗时 : " + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
        System.out.println("完了，集合大小为" + result.size());
    }
}
