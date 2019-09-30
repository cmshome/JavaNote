package com.lxk.thread.countdownlatch;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * CountDownLatch 测试
 * 倒计时器，它允许一个或多个线程一直等待，直到其他线程的操作执行完后再执行。
 *
 * @author lxk on 2018/4/17
 */
public class CountDownLatchTest {
    private static final Integer THREAD_COUNT_NUM = 7;
    private static CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT_NUM);

    /**
     * 缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量。
     * ExecutorService executor = Executors.newCachedThreadPool();
     * 创建固定大小的线程池，可以延迟或定时的执行任务。
     * ScheduledExecutorService executor = Executors.newScheduledThreadPool(THREAD_COUNT_NUM);
     * 创建单个线程池。线程池中只有一个线程
     * ExecutorService executor = Executors.newSingleThreadExecutor();
     */
    @Test
    public void main() throws InterruptedException {
        ExecutorService executor = initExecutor();

        List<String> list = Collections.synchronizedList(Lists.newArrayList());
        for (int i = 0; i < THREAD_COUNT_NUM; i++) {
            int index = i + 1;
            executor.execute(() -> findDragonBall(index, list));
        }
        countDownLatch.await();
        System.out.println("集齐七龙珠，召唤神龙！");
        System.out.println(list.toString());
        executor.shutdown();
    }

    /**
     * 初始化线程池
     */
    private ExecutorService initExecutor() {
        //创建固定大小的线程池
        //阿里建议不使用Executors去创建线程池，因为这个隐藏了线程池的实现
        //使用ThreadPoolExecutor去创建，更加明确线程池的运行规则，规避资源耗尽的风险。
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("my-ThreadPool-%d").build();

        //ExecutorService executor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<>());

        return new ThreadPoolExecutor(7, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<>(),
                namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        /*
        public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler)
     */
    }

    /**
     * 找第index颗星的龙珠
     *
     * @param index 第几颗星第龙珠
     * @param list  存所有龙珠的list
     */
    private void findDragonBall(int index, List<String> list) {
        try {
            int sleepInt = new Random().nextInt(3000);
            Thread.sleep(sleepInt);
            System.out.println("第" + index + "颗龙珠已经收集到！    sleep " + sleepInt);
            list.add(index + "");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }

    }

    /*
        （1）CountDownLatch的计数器只能使用一次。而CyclicBarrier的计数器可以使用reset() 方法重置。
             所以CyclicBarrier能处理更为复杂的业务场景，比如如果计算发生错误，可以重置计数器，并让线程们重新执行一次。
        （2）CyclicBarrier还提供其他有用的方法，比如getNumberWaiting方法可以获得CyclicBarrier阻塞的线程数量。
             isBroken方法用来知道阻塞的线程是否被中断。比如以下代码执行完之后会返回true。
        （3）CountDownLatch会阻塞主线程，CyclicBarrier不会阻塞主线程，只会阻塞子线程。
    */
}
