package com.lxk.jdk.jvm.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.lxk.jdk.jvm.model.runningDataArea.*;
import lombok.Data;

/**
 * Java 运行时数据区域
 *
 * @author LiXuekai on 2019/12/31
 */
@Data
public class JavaRunningDataArea {

    @JSONField
    private ProgramCounterRegister programCounterRegister;

    @JSONField(ordinal = 1)
    private JavaVirtualMachineStacks javaVirtualMachineStacks;

    @JSONField(ordinal = 2)
    private NativeMethodStacks nativeMethodStacks;

    @JSONField(ordinal = 3)
    private JavaHeap heap;

    @JSONField(ordinal = 4)
    private MethodArea methodArea;

}
