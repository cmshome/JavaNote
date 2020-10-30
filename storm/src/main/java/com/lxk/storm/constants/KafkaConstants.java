package com.lxk.storm.constants;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * kafka相关的常量
 *
 * @author LiXuekai on 2020/10/29
 */
public interface KafkaConstants {
    String TOPIC = "topic";
    String PARTITION = "partition";
    String OFFSET = "offset";
    String KEY = "key";
    String VALUE = "value";

    List<String> ALL_FIELD_NAME = Lists.newArrayList(
            TOPIC,
            PARTITION,
            OFFSET,
            KEY,
            VALUE
    );
}
