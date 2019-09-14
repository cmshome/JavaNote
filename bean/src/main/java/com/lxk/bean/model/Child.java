package com.lxk.bean.model;

/**
 * this通常指代当前对象，super通常指代父类
 * <p>
 * @author lxk on 2017/4/26
 */
public class Child extends Parent {


    public Child() {
    }

    public Child(int age, String name) {
        super(age, name);
    }

    @Override
    public void say() {
        System.out.println("this is Child say !");
        super.say();
        //因为属性age在父类中不是private，所以，子类父类都可以访问。一般情况下属性权限都应该设置为private
        System.out.println(super.age);
        System.out.println(this.age);
    }

    /**
     *
     */
    @Override
    protected void eatApple() {
        System.out.println("Child eat apple");
    }
}
