package com.lxk.jdk.jvm.memory.model;

/**
 * 压缩类空间 ccs 区
 * 在 64 位平台上，HotSpot 使用了两个压缩优化技术，Compressed Object Pointers (“CompressedOops”) 和 Compressed Class Pointers。
 * 压缩指针，指的是在 64 位的机器上，使用 32 位的指针来访问数据（堆中的对象或 Metaspace 中的元数据）的一种方式。
 * 这样有很多的好处，比如 32 位的指针占用更小的内存，可以更好地使用缓存，在有些平台，还可以使用到更多的寄存器。
 *
 * @author LiXuekai on 2020/6/13
 */
public class CompressedClassSpace {
}
