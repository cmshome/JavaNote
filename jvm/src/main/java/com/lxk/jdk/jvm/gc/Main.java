package com.lxk.jdk.jvm.gc;

/**
 * -XX:+PrintGCDetails -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
 * 打印gc详细日志 堆20M 年轻代10M，eden ：survivor = 8 ：1，就是eden8M，2个survivor各1M
 *
 * @author LiXuekai on 2020/5/28
 */
public class Main {
    private static final int ONE_MB = 1024 * 1024;

    public static void main(String[] args) {

        byte[] bytes1;
        byte[] bytes2;
        byte[] bytes3;
        byte[] bytes4;
        //bytes1 = new byte[1 * ONE_MB];
        //bytes2 = new byte[2 * ONE_MB];
        //bytes3 = new byte[2 * ONE_MB];
        bytes4 = new byte[9 * ONE_MB];

    }
}
