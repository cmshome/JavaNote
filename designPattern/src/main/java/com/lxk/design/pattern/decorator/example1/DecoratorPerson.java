package com.lxk.design.pattern.decorator.example1;

/**
 * 装饰类
 * (要去增强目标对象的类)
 * 装饰类通常会通过构造方法接收被装饰的对象。
 * 并基于被装饰的对象的功能，提供更强的功能。
 * <p>
 * @author lxk on 2016/11/24
 */
public class DecoratorPerson implements Person {
    private Person p;

    DecoratorPerson(Person p) {
        this.p = p;
    }

    @Override
    public void eat() {
        //简单扩展
        System.out.println("开胃酒");
        p.eat();
        System.out.println("甜点");
        System.out.println("来一根");
    }
}
