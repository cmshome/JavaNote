package com.lxk.design.pattern.singleton;

/**
 * 单例模式第四种
 *
 * @author lxk on 2018/5/16
 */
public class SingletonPattern4 {
    /**
     * 我们把Singleton实例放到一个静态内部类中，这样可以避免了静态实例在Singleton类的加载阶段
     * （类加载过程的其中一个阶段的，此时只创建了Class对象）
     * 就创建对象，毕竟静态变量初始化是在SingletonInner类初始化时触发的，并且由于静态内部类只会被加载一次，所以这种写法也是线程安全的。
     *
     * 考虑反射：
     * 　　由于在调用 Holder.singleton 的时候，才会对单例进行初始化，而且通过反射，是不能从外部类获取内部类的属性的。
     * 　　所以这种形式，很好的避免了反射入侵。
     *
     * 考虑多线程：
     * 　　由于静态内部类的特性，只有在其被第一次引用的时候才会被加载，所以可以保证其线程安全性。
     *
     * 总结：
     * 　　优势：
     *          兼顾了懒汉模式的内存优化（使用时才初始化）以及饿汉模式的安全性（不会被反射入侵）。
     * 　　劣势：
     *          需要两个类去做到这一点，虽然不会创建静态内部类的对象，但是其 Class 对象还是会被创建，而且是属于永久带的对象。
     *          创建的单例，一旦在后期被销毁，不能重新创建。
     */
    private static class Holder {
        private static SingletonPattern4 singleton = new SingletonPattern4();
    }

    private SingletonPattern4() {
        if (Holder.singleton != null) {
            throw new IllegalStateException();
        }
    }

    public static SingletonPattern4 getSingletonInstance() {
        return Holder.singleton;
    }
}
