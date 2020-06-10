package com.lxk.design.pattern.singleton;

/**
 * 懒汉式
 * 这种方式是最基本的实现方式，这种实现最大的问题就是不支持多线程。因为没有加锁 synchronized，所以严格意义上它并不算单例模式。
 *
 * @author lxk on 2017/3/23
 */
public class SingletonPattern2 {
    //这个就不能加final，因为要在其他地方给他再次赋值呢。
    //加了final，那就默认一直是null啦，而且还不能再次给此属性赋值。
    //此属性是静态，那么就是共享数据，多线程并发操作共享数据是有可能的。那么就会出现下面的线程不安全现象。
    private static SingletonPattern2 singletonInstance;

    private SingletonPattern2() {
    }

    public static SingletonPattern2 getSingletonInstance() {
        if (singletonInstance == null) {
            //在这个地方,多线程的时候，
            //可能A线程挂起，此属性还是null，那么B线程可能也判断条件OK也进来啦。
            //然后A线程可以执行的时候就会new个对象，线程B也会new个对象。
            //就不能保证内存的唯一性。也就是线程不安全
            singletonInstance = new SingletonPattern2();
        }
        return singletonInstance;
    }

    ///**
    // * 为了应对上述的不安全，可以简单的如下操作给方法添加[synchronized],使之成为同步函数。
    // * 但是：
    // * 在很多线程的情况下，就每个线程访问都得判断锁，效率就是问题。所以，才有后面的[双重锁形式]
    // */
    //public static synchronized SingletonPattern2 getSingletonInstance() {
    //    if (singletonInstance == null) {
    //        singletonInstance = new SingletonPattern2();
    //    }
    //    return singletonInstance;
    //}
}
