package com.lxk.jdk.fast;

import com.google.common.collect.Lists;
import com.lxk.bean.model.Dog;
import org.junit.Test;

import java.util.List;

/**
 * 测试变量之间的相互引用
 *
 * @author LiXuekai on 2019/8/1
 */
public class NewInOrOutForIsFastTest {
    private static final int MAX = 5000;

    public static void main(String[] args) {
        while (true) {
            //50%
            inFor();
            //50%
            outFor();
        }
    }

    @Test
    public void whichIsFast() {
        while (true) {
            //50%
            inFor();
            //50%
            outFor();
        }
    }

    /**
     * 这个对象在for里面还是外面声明，执行结果一致。list装的都是新new出来的对象。
     */
    @Test
    public void testObject() {
        inFor();
        outFor();
    }

    /**
     * 在for之外声明个对象，在for里面循环赋值。
     */
    private static void outFor() {
        List<Dog> dogs = Lists.newArrayListWithExpectedSize(MAX);
        int i = 0;
        Dog dog;
        while (i < MAX) {
            dog = new Dog(i + "aaa", true, true);
            dogs.add(dog);
            i++;
        }
        //for (Dog d : dogs) {
        //    System.out.println(d.getName());
        //}
    }

    /**
     * 在for里面声明个对象，并赋值。
     */
    private static void inFor() {
        List<Dog> dogs = Lists.newArrayListWithExpectedSize(MAX);
        int i = 0;
        while (i < MAX) {
            Dog dog = new Dog(i + "aaa", true, true);
            dogs.add(dog);
            i++;
        }
        //for (Dog d : dogs) {
        //    System.out.println(d.getName());
        //}
        //System.out.println();
    }
}
