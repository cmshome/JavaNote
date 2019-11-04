package com.lxk.thread.threadpool.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * WorkStealingPool
 *
 * @author LiXuekai on 2019/10/9
 */
public class WorkStealingPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newWorkStealingPool();
    }
}
