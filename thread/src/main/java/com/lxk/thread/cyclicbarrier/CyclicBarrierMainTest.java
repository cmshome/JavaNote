package com.lxk.thread.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * [ok test]
 * 同样的代码，使用main方法去运行，就能正常运行，该sleep就sleep，还都能正常summon 神龙。
 *
 * 可循环使用（Cyclic）的屏障（Barrier）。
 * 它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，
 * 直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。
 *
 * @author lxk on 2018/4/19
 */
public class CyclicBarrierMainTest {
    private static final Integer THREAD_COUNT_NUM = 7;

    public static void main(String[] args) {
        CyclicBarrier callMasterBarrier = new CyclicBarrier(THREAD_COUNT_NUM, new Runnable() {
            @Override
            public void run() {
                System.out.println("7个法师召集完毕，同时出发，去往不同的地方，寻找七龙珠。");
                summonDragon();
            }
        });
        for (int i = 0; i < THREAD_COUNT_NUM; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    int sleepInt = new Random().nextInt(3000);
                    Thread.sleep(sleepInt);
                    System.out.println("召集第" + index + "个法师 sleep " + sleepInt);
                    callMasterBarrier.await();
                } catch (Exception e) {

                }
            }).start();
        }
    }

    private static void summonDragon() {
        CyclicBarrier summonDragonBarrier = new CyclicBarrier(THREAD_COUNT_NUM, new Runnable() {
            @Override
            public void run() {
                System.out.println("集齐七龙珠，召唤神龙。");
            }
        });
        for (int i = 0; i < THREAD_COUNT_NUM; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    int sleepInt = new Random().nextInt(3000);
                    Thread.sleep(sleepInt);
                    System.out.println("第" + index + "个龙珠已经收集到。 sleep " + sleepInt);
                    summonDragonBarrier.await();
                } catch (Exception e) {

                }
            }).start();
        }
    }
}
