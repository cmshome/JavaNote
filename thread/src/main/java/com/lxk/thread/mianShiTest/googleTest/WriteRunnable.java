package com.lxk.thread.mianShiTest.googleTest;

/**
 * @author lxk on 2017/7/14
 */
public class WriteRunnable implements Runnable {
    private final FileWriteUtil util;
    private int threadNum;
    private char value;

    /**
     * @param util      写文件工具类
     * @param threadNum 线程号
     * @param value     写的字符
     */
    public WriteRunnable(FileWriteUtil util, int threadNum, char value) {
        this.util = util;
        this.threadNum = threadNum;
        this.value = value;
    }

    @Override
    public void run() {
        /*
         * 假设循环6次，一直循环可以使用while(true)或者for(;;)
         */
        for (int i = 0; i < 6; i++) {
            synchronized (util) {
                while (threadNum != util.getCurrentThreadNum()) {
                    try {
                        util.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                util.write(value, threadNum);
                util.notifyAll();
            }
        }
    }
}
