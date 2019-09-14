package com.lxk.guava.collection;

/**
 * guava ImmutableMap 测试实例
 *
 * @author lxk on 2017/11/7
 */
public class ImmutableListTest {
    public static void main(String[] args) {
        String string = "最大值";
        if (ConstantList.CONSTANT_LIST.contains(string)) {
            System.out.println("常量list集合包含此 String");
        }
        //此常量list不能add,remove,不然会抛异常的。
        //ConstantList.CONSTANT_LIST.add("sss");
        //ConstantList.CONSTANT_LIST.remove(string);
    }
}
