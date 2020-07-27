package com.lxk.jdk.jvm.memory.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.lxk.jdk.jvm.memory.model.inteface.OutOfMemory;
import com.lxk.jdk.jvm.memory.model.inteface.Shared;
import lombok.Data;

/**
 * Java 堆 内存
 *
 * @author LiXuekai on 2019/12/31
 */
@Data
public class JavaHeap implements OutOfMemory, Shared {

    public JavaHeap() {
        this.javaHeapYoung = new JavaHeapYoung();
        this.javaHeapOld = new JavaHeapOld();
    }

    @JSONField(ordinal = 0)
    private JavaHeapYoung javaHeapYoung;
    @JSONField(ordinal = 1)
    private JavaHeapOld javaHeapOld;

}
