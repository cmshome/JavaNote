package com.lxk.jdk.jvm.model;

import lombok.Data;

/**
 * JMM Java 内存模型 对象
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

}
