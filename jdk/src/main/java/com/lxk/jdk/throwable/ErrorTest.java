package com.lxk.jdk.throwable;

import com.lxk.bean.model.Test;

/**
 * 这俩不叫exception，叫error。是throwable接口下主要的2个实现
 *
 * @author lxk on 2018/3/15
 */
public class ErrorTest {
    public static void main(String[] args) {
        stackOverflowError();
        outOfMemoryError();
    }

    /**
     * 【堆】内存溢出
     *  栈深度大于虚拟机所允许的深度，将抛出StackOverflowError异常.
     *  在这之前要耗尽堆内存，就会出现OOM异常。
     */
    private static void outOfMemoryError() {
        new Test();
        //下面这个反应太慢了
        //List l = new LinkedList();
        // Enter infinite loop which will add a String to the list: l on each
        // iteration.
        //do {
        //    l.add(new String("Hello, World"));
        //} while (true);
    }

    /**
     * 【栈】递归函数炸掉啦
     */
    private static void stackOverflowError() {
        long result = questions7(100000);
        System.out.println(result);
    }

    /**
     * 用递归算法写一段程序实现 1+2+...+1000000
     */
    private static long questions7(long sum) {
        if (sum == 0) {
            return sum;
        }
        return sum + questions7(sum - 1);
    }


}
