package com.lxk.jdk.common;

import org.junit.Test;

/**
 * 测试 try{}catch(){}finally{}的具体执行顺序
 * 结论：
 * try catch finally 都有返回：最终都会得到finally的返回结果。
 * try catch 都有返回 finally没有返回：try出现异常，会得到catch的返回结果。finally中的操作，不影响返回结果。
 * try catch 都有返回 finally没有返回：try没有异常，会得到try的返回结果。  finally中的操作，不影响返回结果。
 *
 * <p>
 *
 * @author lxk on 2016/11/11
 */
public class TryCatchFinallyTest {
    public static void main(String[] args) {
        System.out.println(testFunc1());
        //whenFinallyNoRun();
    }

    /**
     * System.exit(0); //退出jvm，只有这种情况finally不执行。
     * 下面2个return都阻挡不了打印语句的执行。
     */
    @Test
    public void whenFinallyNoRun() {
        try {
            throw new Exception("我抛弃你啦。");
        } catch (Exception exception) {
            System.out.println("catch exception: " + exception.getMessage());
            //jvm stop
            //System.exit(0);
            return;
        } finally {
            System.out.println("finally is running !");
            return;
        }

    }

    /**
     * 在finally里面又修改了最终返回结果，会怎么样？
     * 不管用
     */
    @Test
    public void finallyDo() {
        // 1
        System.out.println(testFunc2());
    }

    /**
     * try里面return的结果在finally中修改有影响吗
     * try正常结束，catch不会执行，finally没有返回，即使执行，也不影响返回结果。
     * 最后返回的就是try里面正常结束的结果：1
     */
    public static int testFunc2() {
        int x = 1;
        try {
            return x;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ++x;
        }
        return x;
    }

    /**
     * try catch finally 中各自return时，函数执行结果。
     */
    private static int testFunc1() {
        String s = null;
        int result;
        try {
            s.equals("ss");
            result = 1;
            System.out.println("try " + result);
            //try 的return语句
            return result;
        } catch (Exception e) {
            result = 2;
            //走，且会给result赋值
            System.out.println("catch " + result);
            //return result;                          //不一定会return
        } finally {
            result = 3;
            System.out.println("finally " + result);
            //return result;                        //这个打开返回的就是finally中的结果 3；关闭返回的就是catch中的结果 2
        }
        //result = 4;
        System.out.println("之外" + result);
        //这个就占个位置，打开finally的return这个返回就永远走不到了，得注释了。
        return result;
    }


    @Test
    public void tt() {
        System.out.println(getReturn());
        System.out.println(getReturnStatic());
    }

    private int getReturn() {
        try {
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            return 3;
        }
    }

    private static int getReturnStatic() {
        try {
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            return 3;
        }
    }
}
