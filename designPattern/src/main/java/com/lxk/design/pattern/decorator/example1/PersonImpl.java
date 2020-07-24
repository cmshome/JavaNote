package com.lxk.design.pattern.decorator.example1;

/**
 * 被装饰的类
 * (被增强对象)
 * <p>
 * @author lxk on 2016/11/24
 */
public class PersonImpl implements Person {
    @Override
    public void eat() {
        System.out.println("吃饭");
    }
}
