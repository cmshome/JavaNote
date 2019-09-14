package com.lxk.guava.collection;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * @author lxk on 2017/11/7
 */
public interface ConstantList {

    /**
     * 有时候，我们可能需要在代码里面使用一个包含许多常量的list
     * 不可变集合。。。
     */
    List<String> CONSTANT_LIST =
            new ImmutableList.Builder<String>()
                    .add("平均值")
                    .add("总值")
                    .add("最大值")
                    .add("最小值")
                    .build();

    /*
     * 推荐使用上面的写法，样式好看，且好维护，也就是说，你现在要删除或者添加一条，直接添加一行就好。常量整体也看着条理清晰。
     */
    //List<String> CONSTANT_LIST = ImmutableList.of("平均值","总值","最大值","最小值");

    //List<String> CONSTANT_LIST = ImmutableList.copyOf(Lists.newArrayList("平均值","总值","最大值","最小值"));
}
