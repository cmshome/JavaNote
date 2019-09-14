package com.lxk.jdk.common;


import com.lxk.bean.enums.*;
import org.junit.Test;

import java.util.List;

/**
 * Java枚举用法测试
 *
 * @author lxk on 2016/12/15
 */
public class EnumTest {

    /**
     * 测试继承接口的枚举的使用
     */
    @Test
    public void testImplementsInterface() {
        for (Food.DessertEnum dessertEnum : Food.DessertEnum.values()) {
            System.out.print(dessertEnum + "  ");
        }
        System.out.println();
        //我这地方这么写，是因为我在自己测试的时候，把这个coffee单独到一个文件去实现那个food接口，而不是在那个接口的内部。
        for (CoffeeEnum coffee : CoffeeEnum.values()) {
            System.out.print(coffee + "  ");
        }
        System.out.println();
        //搞个实现接口，来组织枚举，简单讲，就是分类吧。如果大量使用枚举的话，这么干，在写代码的时候，就很方便调用啦。
        //还有就是个“多态”的功能吧，
        Food food = Food.DessertEnum.CAKE;
        System.out.println(food);
        food = CoffeeEnum.BLACK_COFFEE;
        System.out.println(food);
    }

    @Test
    public void testSwitchCase() {
        String typeName = "f5";
        //这几行注释呢，你可以试着三选一，测试一下效果。
        //String typeName = "firewall";
        //String typeName = "secretMac";
        TypeEnum typeEnum = TypeEnum.fromTypeName(typeName);
        if (typeEnum == null) {
            return;
        }
        switch (typeEnum) {
            case FIREWALL:
                System.out.println("枚举名称(即默认自带的属性 name 的值)是：" + typeEnum.name());
                System.out.println("排序值(默认自带的属性 ordinal 的值)是：" + typeEnum.ordinal());
                System.out.println("枚举的自定义属性 typeName 的值是：" + typeEnum.getTypeName());
                break;
            case SECRET:
                System.out.println("枚举名称(即默认自带的属性 name 的值)是：" + typeEnum.name());
                System.out.println("排序值(默认自带的属性 ordinal 的值)是：" + typeEnum.ordinal());
                System.out.println("枚举的自定义属性 typeName 的值是：" + typeEnum.getTypeName());
                break;
            case BALANCE:
                System.out.println("枚举名称(即默认自带的属性 name 的值)是：" + typeEnum.name());
                System.out.println("排序值(默认自带的属性 ordinal 的值)是：" + typeEnum.ordinal());
                System.out.println("枚举的自定义属性 typeName 的值是：" + typeEnum.getTypeName());
                break;
            default:
                System.out.println("default");
        }
    }

    /**
     * 测试枚举比较,使用equal和==
     */
    @Test
    public void testEnumEqual() {
        GameEnum s1 = GameEnum.BIG;
        GameEnum s2 = GameEnum.BIG;
        GameEnum ss1 = GameEnum.SMALL;
        System.out.println("s1 == s2：" + (s1 == s2));
        System.out.println("s1.equals(s2)：" + (s1.equals(s2)));

        System.out.println("s1 == ss1：" + (s1 == ss1));
        System.out.println("s1.equals(ss1)：" + (s1.equals(ss1)));
    }

    /**
     * 测试新建某个枚举的枚举变量
     *
     * @see GameEnum
     * @see com.lxk.jdk.collection.GetDifferenceSet#getDifferenceSetByGuava(List, List)
     */
    @Test
    public void testNewEnum() {
        GameEnum s = GameEnum.BIG;
        GameEnum ss = GameEnum.SMALL;
        GameEnum sss = GameEnum.FATTER;
    }

    /**
     * 循环枚举,输出ordinal属性；若枚举有内部属性，则也输出。(说的就是我定义的TYPE类型的枚举的typeName属性)
     */
    @Test
    public void forEnum() {
        for (SeasonEnum seasonEnum : SeasonEnum.values()) {
            System.out.println(seasonEnum + "  ordinal  " + seasonEnum.ordinal());
        }
        System.out.println("------------------");
        for (TypeEnum typeEnum : TypeEnum.values()) {
            System.out.println("type = " + typeEnum + "    type.name = " + typeEnum.name() + "   typeName = " + typeEnum.getTypeName() + "   ordinal = " + typeEnum.ordinal());
        }
    }

    /**
     * 在Java代码使用枚举
     */
    @Test
    public void useEnumInJava() {
        String typeName = "f5";
        TypeEnum typeEnum = TypeEnum.fromTypeName(typeName);
        if (TypeEnum.BALANCE.equals(typeEnum)) {
            System.out.println("根据字符串获得的枚举类型实例跟枚举常量一致");
        } else {
            System.out.println("大师兄代码错误");
        }
    }

}
