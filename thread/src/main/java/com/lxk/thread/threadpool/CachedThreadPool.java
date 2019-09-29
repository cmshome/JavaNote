package com.lxk.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
 *
 * @author lxk on 2018/10/8
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        //return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
        //        60L, TimeUnit.SECONDS,
        //        new SynchronousQueue<Runnable>());


        service.execute(() -> System.out.println("HeartBeat........................."));
        service.shutdown();
    }
}
