package com.lxk.jdk.jvm.memory.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.lxk.jdk.jvm.memory.model.running.*;
import lombok.Data;

/**
 * Java 运行时数据区域
 *
 * @author LiXuekai on 2019/12/31
 */
@Data
public class RunningDataArea {

    /**
     * 程序计数器
     */
    @JSONField
    private ProgramCounterRegister programCounterRegister;

    /**
     * Java 虚拟机栈
     */
    @JSONField(ordinal = 1)
    private JVMStacks javaVirtualMachineStacks;

    /**
     * 本地方法栈
     */
    @JSONField(ordinal = 2)
    private NativeMethodStacks nativeMethodStacks;

    /**
     * Java 堆 内存
     */
    @JSONField(ordinal = 3)
    private Heap heap;

    /**
     * 方法区 (从jdk1.8开始，将不再有这个区间)
     */
    @JSONField(ordinal = 4)
    private MethodArea methodArea;


    public RunningDataArea() {
        this.programCounterRegister = new ProgramCounterRegister();
        this.javaVirtualMachineStacks = new JVMStacks();
        this.nativeMethodStacks = new NativeMethodStacks();
        this.heap = new Heap();
        this.methodArea = new MethodArea();
    }
}
