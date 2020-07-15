package com.lxk.jdk.extend;

import com.lxk.bean.model.Child;
import com.lxk.bean.model.Grandson;
import com.lxk.bean.model.Grandson2;
import com.lxk.bean.model.Parent;
import com.lxk.tool.PrintUtil;
import org.junit.Test;

/**
 * 测试继承中的super用法
 *
 * @author lxk on 2017/4/26
 */
public class ExtendTest {
    public static void main(String[] args) {
        testExtend();
    }

    /**
     * 测试super的使用
     */
    private static void testExtend() {
        Child child = new Child();
        child.say();
        child.sayHello();

        PrintUtil.divideLine();

        //java 的多态，说的就是这个。
        Parent parent = new Child();
        parent.say();
    }

    @Test
    public void testEat() {
        Child child = new Child();
        child.eat();
    }

    /**
     * protected 属性在经历了第三次第四次继承的时候，依然能访问。（同一个包下可以访问）
     */
    @Test
    public void testProtected(){
        Grandson grandson = new Grandson();
        grandson.show();
    }

    @Test
    public void testProtected2(){
        Grandson2 grandson = new Grandson2();
        grandson.show();
    }
}
