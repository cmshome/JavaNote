package com.lxk.jdk.system;

import org.junit.Test;

/**
 * 程序退出的原因及是否触发关闭钩子
 * 所有的线程已经执行完毕（√）
 * 调用System.exit()（√）
 * 用户输入Ctrl+C（√）
 * 遇到问题异常退出（√）
 * kill -9 杀掉进程（×）
 * 何时增加关闭钩子：任何时候！！！在任何情况下都可以增加一个关闭钩子，只要在JVM关闭之前。如果试图在JVM开始关闭后注册一个关闭钩子，将抛出一个带有”Shutdown is progress”消息的IllegalStateException。
 * 增加相同的钩子：不能增加相同的钩子。如果这样做了，将抛出带有”Hook previously registered”消息的IllegalArgumentException。
 * 注销钩子：调用Runtime.removeShutdownShook(Thread hook)可以注销一个钩子。
 * 注意并发：万一不止一个关闭钩子，它们将并行地运行，并容易引发线程问题，例如死锁
 * 关闭钩子的可靠性：JVM将在退出的时候尽最大努力来执行关闭钩子，但是不保证一定会执行。例如，当在Linux中使用-kill命令时，或在Windows中终结进程时，由于本地代码被调用，JVM将立即退出或崩溃。
 * 注意钩子的时间消耗：需要注意的重点之一是，关闭钩子不应该花费过多时间。考虑这样一个场景，当用户从操作系统中注销，操作系统花费非常有限的时间就正常退出了，因此在这样样的场景下JVM也应该尽快退出。
 *
 * @author LiXuekai on 2021/1/20
 */
public class ShutdownHook {

    @Test
    public void test() {
        new Thread(() -> {
            System.out.println("new thread...");
            shutdownHook();
        }).start();

    }

    @Test
    public void t() {
        shutdownHook();
    }

    private void shutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread("shutdownHook") {
            @Override
            public void run() {
                System.out.println("shutdownHook...");
            }
        });
    }
}
