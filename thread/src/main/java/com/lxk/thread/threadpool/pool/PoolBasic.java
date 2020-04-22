package com.lxk.thread.threadpool.pool;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadPoolExecutor 类中有下面几个定义，咋一看好像简单，咋一看好像也不简单呐。
 * 源码就是这么定义这几个属性的和简单方法的，可见写代码还真不一定非得按照代码格式要求走，只要你牛逼就行，代码想怎么写都行。
 *
 * @author LiXuekai on 2020/4/7
 */
public class PoolBasic {
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    /*
     * Bit field accessors that don't require unpacking ctl.
     * These depend on the bit layout and on workerCount being never negative.
     */

    private static boolean runStateLessThan(int c, int s) {
        return c < s;
    }

    private static boolean runStateAtLeast(int c, int s) {
        return c >= s;
    }

    private static boolean isRunning(int c) {
        return c < SHUTDOWN;
    }


    /**
     * COUNT_BITS 详解
     */
    @Test
    public void showCountBits() {
        // 29 Integer.SIZE=32 意思就是integer使用了多少位来表达一个int数字。
        System.out.println(Integer.SIZE);
        // 4个字节，一个字节（byte）8位（bit）
        System.out.println(Integer.BYTES);
        System.out.println(COUNT_BITS);
        System.out.println();
    }

    @Test
    public void showCapacity() {
        // 二进制：000 + 29个1，的int值。
        showOne("CAPACITY", CAPACITY);
        // 二进制：111 + 29个0，的int值
        showOne("~CAPACITY", ~CAPACITY);


        int i = 7 & CAPACITY;
        System.out.println("111 & CAPACITY: " + (Integer.toBinaryString(i)));
        System.out.println();

        // 32位中，第一位是0，其它的都是1，即 Integer.MAX_VALUE = 0 + 31 个1
        System.out.println("Integer.MAX_VALUE 二进制：" + Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println("Integer.MAX_VALUE 二进制的长度：" + Integer.toBinaryString(Integer.MAX_VALUE).length());
        i = Integer.MAX_VALUE & ~CAPACITY;
        System.out.println("Integer.MAX_VALUE & ~CAPACITY: " + (Integer.toBinaryString(i)));
        System.out.println("Integer.MAX_VALUE & ~CAPACITY: " + (Integer.toBinaryString(i)).length());
    }

    /**
     * 看看线程池的几个状态数字
     * 这么看就明白了后面29个位，来控制线程的数量count，前面3位来表示线程池的状态，全1的即111表示running，
     * 000-011是递增关系，同时也是线程池状态的衰败过程。
     */
    @Test
    public void showState() {
        //111 - （29个0）
        showOne("RUNNING", RUNNING);
        //000 - （29个0）
        showOne("SHUTDOWN", SHUTDOWN);
        //001 - （29个0）
        showOne("STOP", STOP);
        //010 - （29个0）
        showOne("TIDYING", TIDYING);
        //011 - （29个0）
        showOne("TERMINATED", TERMINATED);
    }

    private void showOne(String name, int aInt) {
        System.out.println(name + "的信息");
        System.out.println("原始int值：" + aInt);
        System.out.println("二进制的值：" + Integer.toBinaryString(aInt));
        System.out.println("二进制的值的字符串的长度：" + Integer.toBinaryString(aInt).length());
        System.out.println("--------------------------");
        System.out.println();
    }

    /**
     * 线程池中的2个数字
     * 1，线程池的状态
     * 2，线程池中的线程数
     */
    @Test
    public void twoCountInPool() {
        int c = ctl.get();
        System.out.println("线程池的2个关键数字。。。");
        poolState(c);
        workerCount(c);
        System.out.println();
        showOne("-1",-1);
    }

    /**
     * 获取线程池的当前状态
     */
    private void poolState(int c) {
        System.out.println("线程池的状态。。。");
        int i = runStateOf(c);
        showOne("test state", i);
        System.out.println();
    }

    /**
     * 获取线程池中当前的线程数
     */
    private void workerCount(int c) {
        System.out.println("线程池的线程数。。。");
        int i = workerCountOf(c);
        System.out.println("worker count is " + i);
    }


}
