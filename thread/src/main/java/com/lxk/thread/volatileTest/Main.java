package com.lxk.thread.volatileTest;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * 重排序导致问题
 * （同时也能验证 使用volatile 是可以禁止指令重排序的观点）
 *
 * @author LiXuekai on 2020/6/11
 */
public class Main {

    private static int x = 0, y = 0;
    private static int a = 0, b = 0;
    private static Set<String> sets = Sets.newHashSet();

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            CountDownLatch latch = new CountDownLatch(1);

            Thread one = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException ignore) {
                }
                a = 1;
                x = b;
            });

            Thread other = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException ignore) {
                }
                b = 1;
                y = a;
            });
            one.start();
            other.start();
            latch.countDown();
            one.join();
            other.join();

            String result = "第" + i + "次 (" + x + "," + y + "）";
            sets.add("" + x + y);
            if (x == 0 && y == 0) {
                System.err.println(result + "   sets is " + sets.toString());
                break;
            } else {
                System.out.println(result + "   sets is " + sets.toString());
            }
        }
    }
}
