package com.lxk.jdk.jvm.model;

import lombok.Data;

/**
 * JMM Java 内存模型 对象
 *
 * @author LiXuekai on 2019/12/31
 */
@Data
public class JavaMemoryModel {
    private JavaRunningDataArea runningDataArea;
}
