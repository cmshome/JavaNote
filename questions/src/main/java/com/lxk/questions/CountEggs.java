package com.lxk.questions;

/**
 * 一个数鸡蛋的程序题
 *
 * @author lxk on 2017/7/7
 */
public class CountEggs {
    public static void main(String[] args) {
        countEggs();
    }

    /**
     * 求答案 ？
     * 一筐鸡蛋：
     * 1个1个拿，正好拿完。
     * 2个2个拿，还剩1个。
     * 3个3个拿，正好拿完。
     * 4个4个拿，还剩1个。
     * 5个5个拿，还差1个。
     * 6个6个拿，还剩3个。
     * 7个7个拿，还剩4个。
     * 8个8个拿，还剩1个。
     * 9个9个拿，正好拿完。
     * 问筐里最少有多少鸡蛋？
     */
    private static void countEggs() {
        for (int i = 0; ; i++) {
            if (i % 2 == 1
                    && i % 3 == 0
                    && i % 4 == 1
                    && i % 5 == 4
                    && i % 6 == 3
                    && i % 7 == 4
                    && i % 8 == 1
                    && i % 9 == 0) {
                System.out.println(i);
                return;
            }
        }
    }

}
