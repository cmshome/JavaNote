package com.lxk.jdk.jvm.memory.model;

import lombok.Data;

/**
 * Java 内存分区
 *
 * @author LiXuekai on 2019/12/31
 */
@Data
public class JavaMemoryModel {
    /**
     * 运行时数据区域
     */
    private JavaRunningDataArea runningDataArea;

    /**
     * 直接内存
     */
    private  DirectMemory directMemory;

    /**
     * 元空间(since jdk 1.8)
     */
    private MetaSpace metaSpace;
}
