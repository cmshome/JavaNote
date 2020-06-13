package com.lxk.jdk.jvm.memory.model;

import lombok.Data;

/**
 * 年轻代
 *
 * @author LiXuekai on 2020/6/13
 */
@Data
public class Young {

    private Eden eden;

    private Survivor survivor0;

    private Survivor survivor1;
}
