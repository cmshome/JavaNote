package com.lxk.lombok;


import com.lxk.bean.extend.XiaoMiTV;

/**
 * 测试@EqualsAndHashCode注解的使用
 *
 * @author LiXuekai on 2019/5/10
 */
public class EqualsAndHashCodeTest {
    public static void main(String[] args) {
        XiaoMiTV tv1 = new XiaoMiTV("123", "lxk", 1L, "白");
        XiaoMiTV tv2 = new XiaoMiTV("456", "sql", 1L, "白");
        System.out.println(tv1.equals(tv2));
    }
}
