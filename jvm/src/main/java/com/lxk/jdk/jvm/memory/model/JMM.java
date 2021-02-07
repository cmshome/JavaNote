package com.lxk.jdk.jvm.memory.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Java 内存分区 JavaMemoryModel
 *
 * @author LiXuekai on 2019/12/31
 */
@Data
public class JMM {
    /**
     * 运行时数据区域
     */
    @JSONField()
    private RunningDataArea runningDataArea;

    /**
     * 直接内存
     */
    @JSONField(ordinal = 1)
    private DirectMemory directMemory;

    /**
     * 元空间(since jdk 1.8)
     */
    @JSONField(ordinal = 2)
    private MetaSpace metaSpace;


    public JMM() {
        this.runningDataArea = new RunningDataArea();
        this.directMemory = new DirectMemory();
        this.metaSpace = new MetaSpace();
    }
}
