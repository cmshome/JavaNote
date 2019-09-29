package com.lxk.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建容量为1的缓冲池
 *
 * @author lxk on 2018/10/8
 */
public class SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();

        //return new FinalizableDelegatedExecutorService
        //        (new ThreadPoolExecutor(1, 1,
        //                0L, TimeUnit.MILLISECONDS,
        //                new LinkedBlockingQueue<Runnable>()));


        service.execute(() -> System.out.println("HeartBeat........................."));
        service.shutdown();
    }
}
