package com.lxk.thread.question.howmany;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试最多能创建多少个线程
 * 4076
 * -Xms2g -Xmx2g -Xss512k
 * -Xms10m -Xmx10m -Xss170k
 *
 * @author LiXuekai on 2020/7/27
 */
public class HowMany {
    private static final ExecutorService service = Executors.newCachedThreadPool();
    private static final AtomicInteger count = new AtomicInteger();
    private static final CountDownLatch countDownLatch = new CountDownLatch(Integer.MAX_VALUE);

    /**
     * 直接造，sleep的停
     */
    @Test
    public void howMany0() {
        while (true) {
            new Thread(() -> {
                System.out.println("thread " + count.addAndGet(1));
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    /**
     * 用池子造，sleep的停下。
     */
    @Test
    public void howMany1() {
        int i = 0;
        while (true) {
            int index = i + 1;
            System.out.println("......................... index is " + index);
            service.execute(() -> {
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    /**
     * 直接造，await。
     */
    @Test
    public void howMany2() {
        while (true) {
            new Thread(() -> {
                System.out.println("thread " + count.addAndGet(1));
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    /**
     * 这个是不带停的吧，之前创建的线程都自己死掉了。
     */
    @Test
    public void howMany3() {
        while (true) {
            new Thread(() -> {
                System.out.println("thread " + count.addAndGet(1));
            }).start();
        }
    }
}
