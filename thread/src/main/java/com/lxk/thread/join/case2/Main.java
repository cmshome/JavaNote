package com.lxk.thread.join.case2;

/**
 * @author lxk on 2018/4/8
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ThreadTest test1 = new ThreadTest();
        ThreadTest test2 = new ThreadTest();
        test1.setName("one");
        test2.setName("two");
        Thread t1 = new Thread(test1);
        Thread t2 = new Thread(test2);
        t1.start();
        /*
         * 主线程向下转时，碰到了t1.join(),t1要申请加入到运行中来，就是要CPU执行权。
         * 这时候CPU执行权在主线程手里，主线程就把CPU执行权给放开，陷入冻结状态。
         * 活着的只有t1了，只有当t1拿着执行权把这些数据都打印完了，主线程才恢复到运行中来
         */
        //join 方法 确保 t1执行之后 执行t2
        t1.join();
        t2.start();
    }
}
