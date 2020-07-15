package com.lxk.thread.syn;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lxk.tool.CollectionUtil;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.*;

/**
 * synchronized test
 *
 * @author LiXuekai on 2020/4/28
 */
public class SynchronizedTest {

    private static Map<String, String> MAP;
    private static ExecutorService executor;

    static {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("CachedThreadPool-%d").build();
        executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * junit测试，主线程完了，子线程直接没了
     */
    @Test
    public void test() {
        MAP = CollectionUtil.getMap(5);
        Task task1 = new Task(1, MAP);
        Task task2 = new Task(3, MAP);
        executor.submit(task1);
        executor.submit(task2);
        executor.shutdown();
    }

    /**
     * 主线程跑完之后，会等子线程结束之后再over
     */
    public static void main(String[] args) {
        System.out.println("          main start ............");
        MAP = CollectionUtil.getMap(50);
        Task task1 = new Task(10, MAP);
        Task task2 = new Task(15, MAP);
        executor.execute(task1);
        executor.execute(task2);

        String name = Thread.currentThread().getName();
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
                String s = MAP.get(i + "");
                System.out.println("          " + name + " i=" + i + " ......" + " value is " + s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("          main executor.shutdown(); ...");
        executor.shutdown();
        System.out.println("          main executor.shutdown(); over...");
        System.out.println("          main over ............");
    }


}

class Task implements Runnable {
    private int time;
    private Map<String, String> map;

    Task(int time, Map<String, String> map) {
        this.time = time;
        this.map = map;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("同步代码块之外的部分。。。。Thread name is " + name);
        synchronized (SynchronizedTest.class) {
            System.out.println("同步代码块内部运行。。。start " + name + " value is " + map.get(time + ""));
            for (int i = 0; i < time; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println(name + " i = " + i + " ......");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("同步代码块内部运行。。。over " + name + "  value is " + map.get(time + ""));

        }

    }
}
