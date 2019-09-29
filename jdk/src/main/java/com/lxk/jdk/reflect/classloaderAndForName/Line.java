package com.lxk.jdk.reflect.classloaderAndForName;

/**
 * @author lxk on 2017/2/21
 */
public class Line {
    //静态代码块:
    //在java中使用static关键字声明的代码块。
    //静态块用于初始化类，为类的属性初始化。每个静态代码块只会执行一次。
    //由于JVM在加载类时会执行静态代码块，所以静态代码块先于主方法执行。
    //如果类中包含多个静态代码块，那么将按照"先定义的代码先执行，后定义的代码后执行"。
    //注意：
    //1 静态代码块不能存在于任何方法体内。
    //2 静态代码块不能直接访问静态实例变量和实例方法，需要通过类的实例对象来访问。
    static {
        System.out.println("静态代码块执行：loading line");
    }

    public static String s = getString();

    private static String getString() {
        System.out.println("给静态变量赋值的静态方法执行：loading line");
        return "ss";
    }

    public static void test() {
        System.out.println("普通静态方法执行：loading line");
    }

    public Line() {
        System.out.println("构造方法执行：loading line");
    }

    //构造代码块：直接在类中定义且没有加static关键字的代码块称为{}构造代码块。
    //构造代码块在创建对象时被调用，每次创建对象都会被调用，并且构造代码块的执行次序优先于类构造函数。
    //这个构造代码块的执行顺序不会因为方法所在位置而影响，我特意将他放在构造函数之后。
    {
        System.out.println("构造代码块执行");
    }
}
