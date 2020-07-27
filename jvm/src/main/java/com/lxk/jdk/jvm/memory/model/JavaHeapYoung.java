package com.lxk.jdk.jvm.memory.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 年轻代
 *
 * @author LiXuekai on 2020/6/13
 */
@Data
public class JavaHeapYoung {
    public JavaHeapYoung() {
        this.javaHeapYoungEden = new JavaHeapYoungEden();
        this.javaHeapYoungSurvivor0 = new JavaHeapYoungSurvivor();
        this.javaHeapYoungSurvivor1 = new JavaHeapYoungSurvivor();
    }

    /**
     * E区
     */
    @JSONField(ordinal = 0)
    private JavaHeapYoungEden javaHeapYoungEden;

    /**
     * S0区
     */
    @JSONField(ordinal = 1)
    private JavaHeapYoungSurvivor javaHeapYoungSurvivor0;

    /**
     * S1区
     */
    @JSONField(ordinal = 2)
    private JavaHeapYoungSurvivor javaHeapYoungSurvivor1;
}
