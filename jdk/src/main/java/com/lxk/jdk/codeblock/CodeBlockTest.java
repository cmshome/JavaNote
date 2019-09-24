package com.lxk.jdk.codeblock;

import com.lxk.bean.model.CodeBlockModel;
import org.junit.Test;

/**
 * 代码块测试
 *
 * @author LiXuekai on 2019/9/24
 */
public class CodeBlockTest {

    /**
     * new 对象的时候，显示静态代码块，再是普通代码块，再是构造函数
     */
    @Test
    public void test1() {
        CodeBlockModel codeBlockModel = new CodeBlockModel();
    }

    /**
     * 虽然不构造对象，但是静态代码块还是会执行的。且执行一次。
     */
    @Test
    public void test2() {
        CodeBlockModel.out();
        CodeBlockModel.out();
        CodeBlockModel.out();
        CodeBlockModel.out();
        CodeBlockModel.out();
    }
}
