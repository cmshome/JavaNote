package com.lxk.jdk.jvm.gc;

import com.google.common.collect.Queues;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 弄个不那么明显的内存泄露的程序，看看jvm的堆区
 * -XX:+PrintGCDetails -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
 *
 * @author LiXuekai on 2020/6/13
 */
public class MemoryLeak {
    private static final LinkedBlockingQueue<String> queue = Queues.newLinkedBlockingQueue();

    public static void main(String[] args) throws Exception {

        Thread one = new Thread(() -> {
            try {
                int i = 0;
                while (true) {
                    queue.put(String.valueOf(i));
                    System.out.println("put " + i);
                    i++;

                }
            } catch (Exception ignore) {
            }
        });

        Thread other = new Thread(() -> {
            try {
                while (true) {
                    //String take = queue.take();
                    //System.out.println(take);
                }
            } catch (Exception ignore) {
            }
        });

        one.start();
        other.start();

        one.join();
        other.join();
        System.out.println("main over...");
    }
}
