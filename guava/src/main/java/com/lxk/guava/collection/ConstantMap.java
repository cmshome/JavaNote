package com.lxk.guava.collection;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 定义一些常量Map<?,?>
 * <p>
 *
 * @author lxk on 2016/11/17
 */
interface ConstantMap {
    Map<Integer, String> INTEGER_STRING_MAP =
            new ImmutableMap.Builder<Integer, String>()

                    .put(30, "IP地址或地址段")
                    .put(31, "端口号或范围")
                    .put(32, "IP地址或地址段")
                    .put(33, "端口号或范围")
                    .put(34, "代码值")
                    .put(38, "探针名称")
                    .put(39, "网络协议号(protocol)")
                    .put(40, "ipv6源IP(ipv6_src_addr)")
                    .put(41, "ipv6目标IP(ipv6_dst_addr)")
                    .put(42, "网络协议名称(protocol_map)")
                    .put(43, "输入接口snmp(input_snmp)")

                    .build();

    /**
     * 这个最多支持5对，不能再多啦。
     */
    ImmutableMap<String,String> MAP = ImmutableMap.of("key1", "value1", "key2", "value2");

    ImmutableMap<String,String> MAP_ = ImmutableMap.copyOf(Maps.newHashMap());

}