package com.lxk.jdk.common;

import com.google.common.collect.Lists;
import com.lxk.bean.model.Car;
import com.lxk.bean.model.Dog;
import com.lxk.bean.model.Person;
import com.lxk.bean.model.Student;
import org.junit.Test;

import java.io.*;
import java.util.List;

/**
 * 测试Java的clone方法
 *
 * @author lxk on 2017/2/28
 */
public class CloneTest {

    /**
     * 使用序列化来实现深拷贝简单。但是，所涉及到的所有对象都的实现序列化接口。
     */
    @Test
    public void cloneBySerializable() {
        Student student1 = new Student();
        Car car = new Car("oooo", 100, Lists.newArrayList(new Dog("aaa", true, true)));
        student1.setCar(car);
        student1.setName("lxk");

        Student student2 = deepCloneObject(student1);
        System.out.println("学生2：" + student2);

        car.setSign("X5");
        car.setMyDog(null);
        student1.setName("xxx");
        System.out.println("学生2：" + student2);
        System.out.println("学生1：" + student1);
    }

    /**
     * 对象包含对象属性的深克隆（override clone方法的深克隆，麻烦。）
     * 所有的对象属性都的重写clone方法。
     */
    @Test
    public void cloneByOverride() {
        Student student1 = new Student();
        Car car = new Car("oooo", 100, Lists.newArrayList(new Dog("aaa", true, true)));
        student1.setCar(car);
        student1.setName("lxk");
        //克隆完之后，student1和student2应该没关系的，修改student1不影响student2的值，但是完之后发现，你修改car的值，student2也受影响啦。
        Student student2 = student1.clone();
        System.out.println("学生2：" + student2);
        //先输出student2刚刚克隆完之后的值，然后在修改student1的相关引用类型的属性值(car)和基本属性值(name)
        car.setSign("X5");
        car.setMyDog(null);
        student1.setName("xxx");
        System.out.println("学生2：" + student2);
        //再次输出看修改的结果
    }

    /**
     * 简单测试：（未有对象嵌套对象的问题）
     * 测试克隆前后，使用的是否是同一个对象，即地址传递和值传递问题
     * 基本简单类型肯定不是地址传递啦，但是要是引用类型的话，就是地址传递啦。
     */
    @Test
    public void testCloneEasy() {
        Person lxk = new Person(18, "lxk");
        List<Person> list = Lists.newArrayList();
        list.add(lxk);
        Person clone = lxk.clone();
        lxk.setAge(100);
        System.out.println(clone.getAge());

    }

    /**
     * 对象的深度克隆，此处的对象涉及Collection接口和Map接口下对象的深度克隆
     * 利用序列化和反序列化的方式进行深度克隆对象
     *
     * @param object 待克隆的对象
     * @param <T>    待克隆对象的数据类型
     * @return 已经深度克隆过的对象
     */
    private static <T extends Serializable> T deepCloneObject(T object) {
        T deepClone = null;
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);

            oos.writeObject(object);

            // 反序列化
            bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bais);
            deepClone = (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (bais != null) {
                    bais.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return deepClone;
    }

}
