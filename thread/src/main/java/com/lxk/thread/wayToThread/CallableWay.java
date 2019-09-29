package com.lxk.thread.wayToThread;

import java.util.concurrent.Callable;

/**
 * Callable和Runnable有几点不同：
 * （1）Callable规定的方法是call()，而Runnable规定的方法是run().
 * （2）Callable的任务执行后可返回值，而Runnable的任务是不能返回值的。
 * （3）call()方法可抛出异常，而run()方法是不能抛出异常的。
 * （4）运行Callable任务可拿到一个Future对象，
 * <p>
 * @author lxk on 2017/6/27
 */
public class CallableWay implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);//毫秒
                sum += 1;
                System.out.println(Thread.currentThread().getName() + "...implements Callable<T>..." + sum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }
}
