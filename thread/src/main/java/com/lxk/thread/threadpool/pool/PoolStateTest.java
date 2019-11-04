package com.lxk.thread.threadpool.pool;

/**
 * 线程池的状态
 *
 * @author LiXuekai on 2019/10/8
 */
public class PoolStateTest {
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
    /**
     * runState is stored in the high-order bits
     */
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;


    /**
     * 打印出来的状态看不懂，哎。
     * 11101                               COUNT_BITS:
     * 11111111111111111111111111111       CAPACITY:
     * 11100000000000000000000000000000    RUNNING:
     * 0                                   SHUTDOWN:
     * 100000000000000000000000000000      STOP:
     * 1000000000000000000000000000000     TIDYING:
     * 1100000000000000000000000000000     TERMINATED:
     */
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(COUNT_BITS) + "                               COUNT_BITS:" );
        System.out.println(Integer.toBinaryString(CAPACITY) + "       CAPACITY:");
        System.out.println(Integer.toBinaryString(RUNNING) + "    RUNNING:");
        System.out.println(Integer.toBinaryString(SHUTDOWN) + "                                   SHUTDOWN:");
        System.out.println(Integer.toBinaryString(STOP) + "      STOP:");
        System.out.println(Integer.toBinaryString(TIDYING) + "     TIDYING:");
        System.out.println(Integer.toBinaryString(TERMINATED) + "     TERMINATED:");
    }
}
