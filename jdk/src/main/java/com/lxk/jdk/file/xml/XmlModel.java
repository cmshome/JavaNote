package com.lxk.jdk.file.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 配置文件节点 model
 *
 * @author lxk on 2016/11/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XmlModel {
    /**
     * 流ID
     */
    private List<String> streamIds;
    /**
     * 流名称
     */
    private String name;


    @Override
    public String toString() {
        return "单个节点{" +
                "streamIds=" + streamIds +
                ", name='" + name + '\'' +
                '}';
    }
}
