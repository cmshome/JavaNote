package com.lxk.thread.countdownlatch;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.*;

/**
 * CountDownLatch 测试 2
 *
 * @author LiXuekai on 2019/10/8
 */
public class CountDownLatch2Test {

    private static final int MAX = 10;

    /**
     * 开始的倒数锁
     */
    private static final CountDownLatch BEGIN = new CountDownLatch(1);
    /**
     * 结束的倒数锁
     */
    private static final CountDownLatch END = new CountDownLatch(MAX);
    /**
     * 十名选手
     */
    private static final ExecutorService EXEC = initExecutor();


    public static void main(String[] args) throws InterruptedException {

        for (int index = 0; index < MAX; index++) {
            final int number = index + 1;
            Runnable run = () -> {
                try {
                    // 10个人都到同一出发点等待一起出发。
                    BEGIN.await();
                    long now = System.currentTimeMillis();
                    System.out.println("No." + number + " start, now is " + now);
                    int nextInt = new Random().nextInt(3000);
                    Thread.sleep(nextInt);
                    System.out.println("No." + number + " arrived, use " + nextInt + " ms");
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                } finally {
                    // 每个选手到达终点时，end就减一
                    END.countDown();
                }
            };
            EXEC.submit(run);
        }
        System.out.println("Game Start");
        // begin减一，开始游戏
        BEGIN.countDown();
        // 等待end变为0，即所有选手到达终点
        END.await();
        System.out.println("Game Over");
        EXEC.shutdown();
    }

    /**
     * {@link Executors#newFixedThreadPool(int)}
     */
    private static ExecutorService initExecutor() {
        return new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat("my-ThreadPool-%d").build());
    }
}
