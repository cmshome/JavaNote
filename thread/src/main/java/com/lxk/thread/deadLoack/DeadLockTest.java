package com.lxk.thread.deadLoack;

class Test implements Runnable {
    private boolean flag;

    Test(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            while (true) {
                synchronized (MyLock.lockA) {
                    System.out.println(Thread.currentThread().getName() + "...if lockA ");
                    synchronized (MyLock.lockB) {
                        System.out.println(Thread.currentThread().getName() + "..if lockB");
                    }
                }
            }
        } else {
            while (true) {
                synchronized (MyLock.lockB) {
                    System.out.println(Thread.currentThread().getName() + "..else lockB");
                    synchronized (MyLock.lockA) {
                        System.out.println(Thread.currentThread().getName() + ".....else lockA");
                    }
                }
            }
        }
    }
}


class MyLock {
    static final Object lockA = new Object();
    static final Object lockB = new Object();
}

class DeadLockTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Test(true));
        Thread t2 = new Thread(new Test(false));
        t1.start();
        t2.start();
    }
}
