package com.lxk.vertx.elastic.module;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 获取配置的信息的对象
 * (@JSONField(ordinal = x) 注解中的x控制转json之后的key的顺序，跟配置文件中的key顺序保持一致，方便日志和配置对比。)
 *
 * @author LiXuekai on 2019/7/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Config {
    /**
     * es服务器地址，从配置文件获取
     */
    private String esHost;
    /**
     * es服务的端口，从配置文件获取
     */
    @JSONField(ordinal = 1)
    private String esPort;
    /**
     * 统计索引的名称
     */
    @JSONField(ordinal = 2)
    private String summaryIndexName;
    /**
     * 开始时间
     */
    @JSONField(ordinal = 3)
    private String fromTime;
    /**
     * 结束时间
     */
    @JSONField(ordinal = 4)
    private String toTime;
    /**
     * 流ID
     */
    @JSONField(ordinal = 5)
    private List<String> streamIds;
    /**
     * 时间字段在es中的key的名称，查es语句的时候用
     */
    @JSONField(ordinal = 6)
    private String startTimeKey;
    /**
     * 时间字段在es中的key的名称，查es语句的时候用
     */
    @JSONField(ordinal = 7)
    private String endTimeKey;
    /**
     * 流字段在es中的key的名称，查es语句的时候用
     */
    @JSONField(ordinal = 8)
    private String statisticalKey;


}
