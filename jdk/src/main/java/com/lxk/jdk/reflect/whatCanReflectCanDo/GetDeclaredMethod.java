package com.lxk.jdk.reflect.whatCanReflectCanDo;


import com.lxk.bean.model.Dog;

import java.lang.reflect.Method;

/**
 * getDeclaredMethod和invoke
 *
 * @author lxk on 2018/4/23
 */
public class GetDeclaredMethod {
    public static void main(String[] args) {
        Dog dog = new Dog("大师兄的dog", true, true);
        Class<? extends Dog> dogClass = dog.getClass();
        try {
            Method getNameMethod = dogClass.getDeclaredMethod("bark",String.class);
            getNameMethod.invoke(new Dog(), "反射的参数");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
