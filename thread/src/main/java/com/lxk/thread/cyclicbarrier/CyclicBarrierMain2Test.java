package com.lxk.thread.cyclicbarrier;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.*;

/**
 * [ok test]
 * CyclicBarrier test 使用 main 方法来测试
 *
 * @author LiXuekai on 2019/9/30
 */
public class CyclicBarrierMain2Test {
    private static final Integer THREAD_COUNT_NUM = 7;
    private static ExecutorService executor = initExecutor();


    public static void main(String[] args) {
        CyclicBarrier callMasterBarrier = new CyclicBarrier(THREAD_COUNT_NUM, CyclicBarrierMain2Test::goAndFindAllDragonBall);
        for (int i = 0; i < THREAD_COUNT_NUM; i++) {
            int index = i + 1;
            executor.execute(() -> callTheMasterTogether(index, callMasterBarrier));
        }
    }

    /**
     * 召集法师
     *
     * @param index             第n个法师
     * @param callMasterBarrier 召集法师的屏障
     */
    private static void callTheMasterTogether(int index, CyclicBarrier callMasterBarrier) {
        try {
            int sleepInt = new Random().nextInt(3000);
            Thread.sleep(sleepInt);
            System.out.println("召集第" + index + "个法师  sleep " + sleepInt);
            callMasterBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 人齐了，就出发找龙珠去
     */
    private static void goAndFindAllDragonBall() {
        System.out.println("7个法师召集完毕，同时出发，去往不同的地方，寻找七龙珠。");
        findAndSummonDragon();
    }

    /**
     * 集龙珠and召神龙
     */
    private static void findAndSummonDragon() {
        CyclicBarrier summonDragonBarrier = new CyclicBarrier(THREAD_COUNT_NUM, CyclicBarrierMain2Test::summonDragon);
        for (int i = 0; i < THREAD_COUNT_NUM; i++) {
            int index = i + 1;
            executor.execute(() -> findDragonBall(index, summonDragonBarrier));
        }
    }

    /**
     * 召唤神龙
     * summon 召唤的意思
     */
    private static void summonDragon() {
        System.out.println("集齐七龙珠，召唤神龙。");
        executor.shutdown();
    }

    /**
     * 找龙珠
     *
     * @param index               第n个龙珠
     * @param summonDragonBarrier 找龙珠的屏障
     */
    private static void findDragonBall(int index, CyclicBarrier summonDragonBarrier) {
        try {
            int sleepInt = new Random().nextInt(3000);
            Thread.sleep(sleepInt);
            System.out.println("第" + index + "个龙珠已经收集到。 sleep " + sleepInt);
            summonDragonBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化线程池
     */
    private static ExecutorService initExecutor() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("my-ThreadPool-%d").build();
        return new ThreadPoolExecutor(14, 20, 5, TimeUnit.MINUTES, new SynchronousQueue<>(),
                namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    }
}
