package com.lxk.jdk.jvm.memory.model.running;

import com.alibaba.fastjson.annotation.JSONField;
import com.lxk.jdk.jvm.memory.inteface.OutOfMemory;
import com.lxk.jdk.jvm.memory.model.MetaSpace;
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
    @JSONField()
    private String part0 = "从jdk1.8开始，将不再有这个区间";
    @JSONField(ordinal = 1)
    private String part1 = "类及其元数据的JVM内部表示";
    @JSONField(ordinal = 2)
    private String part2 = "类的静态";
    @JSONField(ordinal = 3)
    private ConstantPool constantPool;


    public MethodArea() {
        this.constantPool = new ConstantPool();
    }


    @Data
    class ConstantPool {
        private String part0 = "jdk1.7的时候，常量池已经被安排在堆";
    }
}
