package com.lxk.jdk.serialize;


import com.lxk.bean.model.Bird;
import com.lxk.bean.model.Dog;
import com.lxk.bean.model.FlyPig;
import com.lxk.tool.JsonUtils;

import java.io.*;
import java.util.Date;

/**
 * 序列化测试
 *
 * @author lxk on 2017/11/1
 */
public class SerializableTest {
    public static void main(String[] args) throws Exception {
        //serializeFlyPig();
        FlyPig flyPig = deserializeFlyPig();
        System.out.println(flyPig.toString());
    }

    /**
     * 序列化
     * 属性是对象的时候，这个对象的类也需要实现序列化接口，不然会报错。
     */
    private static void serializeFlyPig() throws IOException {
        String dog = JsonUtils.parseObjToJson(new Dog("大师兄的dog", true, true));
        Bird bird = new Bird(dog, dog, new Date(), "红色皮肤", "键盘侠", 18, "典韦", "不序列化的字段，是不会被转json输出的");
        FlyPig flyPig = new FlyPig();
        flyPig.setName("naruto");
        flyPig.setColor("black");
        flyPig.setCar("0000");
        flyPig.setAddTip("13234");
        flyPig.setBird(bird);

        // ObjectOutputStream 对象输出流，将 flyPig 对象存储到E盘的 flyPig.txt 文件中，完成对 flyPig 对象的序列化操作
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("/Users/fang/Downloads/flyPig.txt")));
        oos.writeObject(flyPig);
        System.out.println("FlyPig 对象序列化成功！");
        oos.close();
    }

    /**
     * 反序列化
     */
    private static FlyPig deserializeFlyPig() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("/Users/fang/Downloads/flyPig.txt")));
        FlyPig person = (FlyPig) ois.readObject();
        System.out.println("FlyPig 对象反序列化成功！");
        return person;
    }
}
