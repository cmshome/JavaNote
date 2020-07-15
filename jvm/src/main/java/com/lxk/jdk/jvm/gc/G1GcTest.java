package com.lxk.jdk.jvm.gc;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * G1 gc test
 * -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xms20M -Xmx20M -XX:+UseG1GC
 *
 * @author LiXuekai on 2020/6/29
 */
public class G1GcTest {
    private static final int ONE_MB = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {
        List<byte[]> list = Lists.newArrayList();
        while (true){
            increaseHeap(list);
        }
    }

    private static void increaseHeap(List<byte[]> list) throws InterruptedException {
        byte[] bytes = new byte[1024];
        list.add(bytes);
        int i = 0;
        while (i < 1) {
            //System.out.print(".");
            TimeUnit.MILLISECONDS.sleep(10);
            i++;
        }
    }
}
