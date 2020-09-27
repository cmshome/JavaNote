package com.lxk.storm.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Builder;
import lombok.Data;

import java.util.TreeMap;

/**
 * @author LiXuekai on 2020/9/25
 */
@Data
@Builder
public class RepeatEvent {

    @JSONField(ordinal = 0)
    private String dataId;

    @JSONField(ordinal = 1)
    private int count;

    @JSONField(ordinal = 2)
    private TreeMap<String, Integer> risk;

}
