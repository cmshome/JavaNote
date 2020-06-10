package com.lxk.jdk.jvm.gc;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 说是jdk1.8之后，没有了方法区，永久代最终被移除，方法区移至Metaspace，字符串常量移至Java Heap。
 * -XX:+PrintGCDetails -Xms10M -Xmx10M -Xmn10M -XX:SurvivorRatio=8
 *
 * @author LiXuekai on 2020/6/9
 */
public class StringConstantPoolTest {


    /**
     * 测试是Java heap 溢出，但是，使用1.7的jdk测试这段代码，还是Java heap溢出。
     * 怎么解释。
     */
    public static void main(String[] args) {
        List<String> list = Lists.newArrayList();
        while (true) {
            //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
            //at java.util.Arrays.copyOf(Arrays.java:3210)
            //at java.util.Arrays.copyOf(Arrays.java:3181)
            //at java.util.ArrayList.grow(ArrayList.java:265)
            //at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:239)
            //at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:231)
            //at java.util.ArrayList.add(ArrayList.java:462)
            //at com.lxk.jdk.jvm.gc.StringConstantPoolTest.main(StringConstantPoolTest.java:23)

            list.add(String.valueOf(System.currentTimeMillis()).intern());


            //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
            //at java.lang.Long.toString(Long.java:399)
            //at java.lang.String.valueOf(String.java:3113)
            //at com.lxk.jdk.jvm.gc.StringConstantPoolTest.main(StringConstantPoolTest.java:24)

            //list.add(String.valueOf(System.currentTimeMillis()));
        }
    }
}
