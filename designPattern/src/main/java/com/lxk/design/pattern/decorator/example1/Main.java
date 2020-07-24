package com.lxk.design.pattern.decorator.example1;

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
        // 这个是需求1.0
        new PersonImpl().eat();
        // 向一个现有的对象添加新的功能，同时又不改变其结构。
        new DecoratorPerson(new PersonImpl()).eat();
    }
}
