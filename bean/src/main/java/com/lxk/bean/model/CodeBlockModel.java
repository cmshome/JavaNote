package com.lxk.bean.model;

import lombok.Data;

/**
 * 为测试代码块而写的model
 * <p>
 *
 * @author lxk on 2017/2/16
 */
@Data
public class CodeBlockModel {
    private String name;
    private String age;
    private String job;

    static {
        System.out.println("静态 构造代码块");
    }

    {
        System.out.println("构造代码块");
        this.name = "default name";
    }

    public CodeBlockModel() {
        System.out.println("无参数构造函数");
    }

    public CodeBlockModel(String name, String age, String job) {
        System.out.println("有参数构造函数");
        this.name = name;
        this.age = age;
        this.job = job;
    }

    public static void out(){
         System.out.println("静态代码的执行的时候。。。。");
    }

}
