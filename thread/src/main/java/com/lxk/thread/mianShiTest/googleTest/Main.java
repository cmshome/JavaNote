package com.lxk.thread.mianShiTest.googleTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 有四个线程1、2、3、4。
 * <p>
 * 线程1的功能就是输出1，线程2的功能就是输出2，
 * <p>
 * 以此类推......... 现在有四个文件A B C D,
 * 初始都为空。现要让四个文件呈如下格式：
 * A：1 2 3 4 1 2....
 * B：2 3 4 1 2 3....
 * C：3 4 1 2 3 4....
 * D：4 1 2 3 4 1....
 * <p>
 * @author lxk on 2017/7/14
 */
public class Main {
    public static void main(String[] args) {
        FileWriteUtil util = new FileWriteUtil();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new WriteRunnable(util, 1, '1'));
        service.execute(new WriteRunnable(util, 2, '2'));
        service.execute(new WriteRunnable(util, 3, '3'));
        service.execute(new WriteRunnable(util, 4, '4'));
        service.shutdown();

        //new Thread(new WriteRunnable(util, 1, '1')).start();
        //new Thread(new WriteRunnable(util, 2, '2')).start();
        //new Thread(new WriteRunnable(util, 3, '3')).start();
        //new Thread(new WriteRunnable(util, 4, '4')).start();
    }
}
