package com.lxk.jdk.jvm.memory.model;

import com.lxk.jdk.jvm.memory.model.inteface.OutOfMemory;
import lombok.Data;

/**
 * 方法区(从jdk1.8开始，将不再有这个区间)
 * @see MetaSpace
 *
 * @author LiXuekai on 2019/12/31
 */
@Deprecated
@Data
public class MethodArea implements OutOfMemory {

    /**
     * JVM internal representation of classes and their metadata
     */
    private String part1 = "类及其元数据的JVM内部表示";
    /**
     * Class statics
     */
    private String part2 = "类的静态";

    /**
     * Interned strings
     */
    private ConstantPool constantPool;
}
