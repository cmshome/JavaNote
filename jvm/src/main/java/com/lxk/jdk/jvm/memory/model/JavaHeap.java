package com.lxk.jdk.jvm.memory.model;

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

    private Young young;

    private Old old;

}
