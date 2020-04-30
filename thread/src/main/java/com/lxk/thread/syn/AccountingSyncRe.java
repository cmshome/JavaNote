package com.lxk.thread.syn;

/**
 * synchronized 的可重入性
 * <p>
 * 从互斥锁的设计上来说，当一个线程试图操作一个由其他线程持有的对象锁的临界资源时，将会处于阻塞状态，
 * 但当一个线程再次请求自己持有对象锁的临界资源时，这种情况属于重入锁，请求将会成功，
 * 在java中synchronized是基于原子性的内部锁机制，是可重入的，
 * 因此在一个线程调用synchronized方法的同时在其方法体内部调用该对象另一个synchronized方法，
 * 也就是说一个线程得到一个对象锁后再次请求该对象锁，是允许的，这就是synchronized的可重入性。
 *
 * @author LiXuekai on 2020/4/30
 */
public class AccountingSyncRe implements Runnable {
    private static AccountingSyncRe instance = new AccountingSyncRe();
    private static int i = 0;
    private static int j = 0;

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {

            //this,当前实例对象锁
            synchronized (this) {
                i++;
                //synchronized的可重入性
                increase();
            }
        }
    }

    private synchronized void increase() {
        j++;
    }


    /**
     * java中synchronized是基于原子性的内部锁机制，是可重入的
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(i);
        System.out.println(j);
    }

    // 正如代码所演示的，在获取当前实例对象锁后进入synchronized代码块执行同步代码，
    // 并在代码块中调用了当前实例对象的另外一个synchronized方法，再次请求当前实例锁时，将被允许，进而执行方法体代码，这就是重入锁最直接的体现，
    // 需要特别注意另外一种情况，当子类继承父类时，子类也是可以通过可重入锁调用父类的同步方法。注意由于synchronized是基于monitor实现的，
    // 因此每次重入，monitor中的计数器仍会加1。
}
