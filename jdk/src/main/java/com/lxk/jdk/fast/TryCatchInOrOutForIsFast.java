package com.lxk.jdk.fast;


import com.lxk.bean.model.Dog;

/**
 * 据说 try catch 在for循环体中会影响效率，不服来测测。
 *
 * @author LiXuekai on 2019/7/19
 */
public class TryCatchInOrOutForIsFast {
    private static final int FOR_MAX = 1000;
    private static final int FOR_MAX_ = 999;

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        while (true) {
            testNoException();
            //testException();
        }
    }

    /**
     * 在 try catch 中【会】抛异常
     */
    private static void testException() {
        out();
        in();
    }

    /**
     * 在try catch 中【不会】抛异常
     */
    private static void testNoException() {
        outNoException();
        inNoException();
    }

    private static void in() {
        int i = 0;
        while (i < FOR_MAX) {
            try {
                Dog dog = new Dog();
                //确保最后一次抛异常，大家循环次数一致
                if (i == FOR_MAX_) {
                    throw new Exception("xxxxxxxxx");
                }
            } catch (Exception e) {
            }
            i++;
        }
    }

    private static void out() {
        int i = 0;
        try {
            while (i < FOR_MAX) {
                Dog dog = new Dog();
                //确保最后一次抛异常，大家循环次数一致 不能上来就抛异常，那么这个循环次数就比上面少了。
                if (i == FOR_MAX_) {
                    throw new Exception("xxxxxxxxx");
                }
                i++;
            }
        } catch (Exception e) {
        }

    }

    private static void outNoException() {
        int i = 0;
        try {
            while (i < FOR_MAX) {
                Dog dog = new Dog();
                if (i == FOR_MAX_) {
                    new Exception("xxxxxxxxx");
                }
                i++;
            }
        } catch (Exception e) {
        }
    }

    private static void inNoException() {
        int i = 0;
        while (i < FOR_MAX) {
            try {
                Dog dog = new Dog();
                if (i == FOR_MAX_) {
                    new Exception("xxxxxxxxx");
                }
            } catch (Exception e) {
            }
            i++;
        }
    }
}
