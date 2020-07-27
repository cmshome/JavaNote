package com.lxk.jdk.jvm.memory.model;

import lombok.Data;

/**
 * 年轻代
 *
 * @author LiXuekai on 2020/6/13
 */
@Data
public class JavaHeapYoung {

    /**
     * E区
     */
    private JavaHeapYoungEden javaHeapYoungEden;

    /**
     * S0区
     */
    private JavaHeapYoungSurvivor javaHeapYoungSurvivor0;

    /**
     * S1区
     */
    private JavaHeapYoungSurvivor javaHeapYoungSurvivor1;
}
