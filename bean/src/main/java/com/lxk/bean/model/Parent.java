package com.lxk.bean.model;

/**
 * @author lxk on 2017/4/26
 */
public class Parent {
    protected  String lastName = "李";
    public int age = 18;

    private String name = "lxk";

    public Parent() {
    }

    public Parent(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void say() {
        System.out.println("this is parent say !");
    }

    public void sayHello() {
        System.out.println("this is parent sayHello !");
    }

    public void eat(){
        eatApple();
        eatRice();
    }

    private void eatRice() {
        System.out.println("parent eat rice。");
    }

    protected void eatApple() {
        System.out.println("parent eat apple");
    }

}
