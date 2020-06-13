package com.lxk.jdk.jvm.memory.model;

import com.lxk.jdk.jvm.memory.model.inteface.OutOfMemory;
import com.lxk.jdk.jvm.memory.model.inteface.Shared;
import lombok.Data;

/**
 * M区
 * 区域位于堆外, 用来存放 class metadata
 * 虽然每个 Java 类都关联了一个 java.lang.Class 的实例，而且它是一个贮存在堆中的 Java 对象。
 * 但是类的 class metadata 不是一个 Java 对象，它不在堆中，而是在 Metaspace 中。
 *
 * @author LiXuekai on 2020/6/13
 */
@Data
public class MetaSpace implements Shared, OutOfMemory {

    private String part0 = "Klass 结构，这个非常重要，把它理解为一个 Java 类在虚拟机内部的表示吧";
    private String part1 = "method metadata，包括方法的字节码、局部变量表、异常表、参数信息等";
    private String part2 = "常量池";
    private String part3 = "注解";
    private String part4 = "方法计数器，记录方法被执行的次数，用来辅助 JIT 决策";
    private String part5 = "其他";

}
