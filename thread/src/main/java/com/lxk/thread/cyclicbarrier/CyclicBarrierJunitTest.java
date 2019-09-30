package com.lxk.thread.cyclicbarrier;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * [error test]
 * 这个使用 junit测试，就会出现有时候没有找到7个龙珠，召唤神龙。。。。。。。。
 * 可循环使用（Cyclic）的屏障（Barrier）。
 * 它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，
 * 直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。
 *
 * @author lxk on 2018/4/19
 */
public class CyclicBarrierJunitTest {
    private static final Integer THREAD_COUNT_NUM = 7;
    private static ExecutorService executor = initExecutor();

    @Test
    public void main() {
        CyclicBarrier callMasterBarrier = new CyclicBarrier(THREAD_COUNT_NUM, this::goAndFindAllDragonBall);
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
    private void callTheMasterTogether(int index, CyclicBarrier callMasterBarrier) {
        try {
            System.out.println("召集第" + index + "个法师");
            callMasterBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 人齐了，就出发找龙珠去
     */
    private void goAndFindAllDragonBall() {
        System.out.println("7个法师召集完毕，同时出发，去往不同的地方，寻找七龙珠。");
        findAndSummonDragon();
    }

    /**
     * 集龙珠and召神龙
     */
    private void findAndSummonDragon() {
        CyclicBarrier summonDragonBarrier = new CyclicBarrier(THREAD_COUNT_NUM, this::summonDragon);
        for (int i = 0; i < THREAD_COUNT_NUM; i++) {
            int index = i + 1;
            executor.execute(() -> findDragonBall(index, summonDragonBarrier));
        }
    }

    /**
     * 召唤神龙
     * summon 召唤的意思
     */
    private void summonDragon() {
        System.out.println("集齐七龙珠，召唤神龙。");
        executor.shutdown();
    }

    /**
     * 找龙珠
     *
     * @param index               第n个龙珠
     * @param summonDragonBarrier 找龙珠的屏障
     */
    private void findDragonBall(int index, CyclicBarrier summonDragonBarrier) {
        System.out.println("第" + index + "个龙珠已经收集到。");
        try {
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
