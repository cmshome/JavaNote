package com.lxk.jdk.jvm.gc;


import com.google.common.collect.Lists;

import java.util.List;

/**
 * -XX:+PrintGCDetails -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
 * 打印gc详细日志 堆20M 年轻代10M，eden ：survivor = 8 ：1，就是eden8M，2个survivor各1M
 *
 * @author LiXuekai on 2020/5/28
 */
public class GcInfoTest {
    private static final int ONE_MB = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("程序开始。。。。给30s准备时间。");
        //TimeUnit.SECONDS.sleep(30);
        System.out.println("30s准备时间over，开始消费堆空间。。。。");

        List<byte[]> list = Lists.newArrayList();
        int i = 0;
        int max = 18;
        while (i < max) {
            System.out.println("在堆分配1M空间");
            increaseHeap(list);
            System.out.println("");
            System.out.println("在堆上已经分配了 " + (i + 1) + " M 空间");
            i++;
        }
        System.out.println("all over 。。。。。。");
    }

    private static void increaseHeap(List<byte[]> list) throws InterruptedException {
        byte[] bytes = new byte[ONE_MB];
        list.add(bytes);
        int i = 0;
        while (i < 30) {
            System.out.print(".");
            //TimeUnit.SECONDS.sleep(1);
            i++;
        }
    }
}
