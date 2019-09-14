package com.lxk.design.pattern.decorator;

/**
 * 装饰设计模式：
 * 当想要对已有的对象进行功能增强时，
 * 可以定义类，将已有对象传入，基于已有的功能，并提供加强功能。
 * 那么自定义的该类称为装饰类。
 * <p>
 * 装饰类通常会通过构造方法接收被装饰的对象。
 * 并基于被装饰的对象的功能，提供更强的功能。
 *
 * @author lxk
 */

public class Main {
    public static void main(String[] args) {
        PersonBefore p = new PersonBefore();
        //p.eat();//这个是需求1.0
        PersonNow sp = new PersonNow(p);
        sp.eat();
    }
}
