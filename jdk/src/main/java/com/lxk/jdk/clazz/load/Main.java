package com.lxk.jdk.clazz.load;

/**
 * @author lxk on 2018/3/15
 */
public class Main {
    public static void main(String[] args) {

        MyClassLoader classLoader = new MyClassLoader();
        classLoader.setRoot("d:Student.class");

        Class<?> testClass;
        try {
            testClass = classLoader.loadClass("com/lxk/model/Student");
            Object object = testClass.newInstance();
            System.out.println(object.getClass().getClassLoader());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
