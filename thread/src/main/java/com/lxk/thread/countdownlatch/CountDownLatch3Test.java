package com.lxk.thread.countdownlatch;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lxk.bean.model.Dog;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.*;

/**
 * 测试
 *
 * @author LiXuekai on 2020/4/16
 */
public class CountDownLatch3Test {
    private static final Integer MESSAGE_SIZE = 10;

    private static CountDownLatch countDownLatch = new CountDownLatch(MESSAGE_SIZE);

    @Test
    public void tt() throws InterruptedException {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("my-ThreadPool-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        List<Dog> dogs = Lists.newArrayList();
        for (int i = 0; i < MESSAGE_SIZE; i++) {
            dogs.add(new Dog(String.valueOf(i), true, true));
        }
        dogs.forEach(dog -> System.out.println(dog.getName()));

        for (Dog dog : dogs) {
            threadPoolExecutor.execute(() -> {
                dog.setName("lll");
                try {
                    long taskCount = threadPoolExecutor.getTaskCount();
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "  taskCount   " +taskCount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println();
        dogs.forEach(dog -> System.out.println(dog.getName()));


    }

}
