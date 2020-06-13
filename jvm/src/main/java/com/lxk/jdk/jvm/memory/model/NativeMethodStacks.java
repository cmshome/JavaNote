package com.lxk.jdk.jvm.memory.model;

import com.lxk.jdk.jvm.memory.model.inteface.OutOfMemory;
import com.lxk.jdk.jvm.memory.model.inteface.StackOverflow;
import com.lxk.jdk.jvm.memory.model.inteface.Unshared;

/**
 * 本地方法栈
 *
 * @author LiXuekai on 2019/12/31
 */
public class NativeMethodStacks implements OutOfMemory, StackOverflow, Unshared {
}
