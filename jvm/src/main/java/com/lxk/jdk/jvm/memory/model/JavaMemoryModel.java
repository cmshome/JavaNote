package com.lxk.jdk.jvm.memory.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Java 内存分区
 *
 * @author LiXuekai on 2019/12/31
 */
@Data
public class JavaMemoryModel {

    public JavaMemoryModel() {
        this.runningDataArea = new JavaRunningDataArea();
        this.directMemory = new DirectMemory();
        this.metaSpace = new MetaSpace();
    }

    /**
     * 运行时数据区域
     */
    @JSONField()
    private JavaRunningDataArea runningDataArea;

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
}
