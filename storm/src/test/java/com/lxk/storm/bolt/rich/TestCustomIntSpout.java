package com.lxk.storm.bolt.rich;

import com.lxk.storm.bolt.BaseBoltTest;
import com.lxk.storm.spout.CustomIntSpout;
import com.lxk.tool.JsonUtils;
import org.apache.storm.topology.TopologyBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static com.lxk.storm.constants.CustomConstants.*;

/**
 * 测试自定义 int spout
 *
 * @author LiXuekai on 2020/10/14
 */
public class TestCustomIntSpout extends BaseBoltTest {


    @Before
    public void init() {
        TOPOLOGY_NAME = "custom-int-spout-topology";
    }

    @Test
    public void test() {

        //1，实例化 kafka spout
        customIntSpout(builder);

        //3，bolt 消费数据
        outInfoBolt(builder, "lxk1", OUT_INFO_BOLT_ID);
        outInfoBolt(builder, "lxk2", OUT_INFO_BOLT_ID_2);

        //4，提交
        submitTopology();
    }


    @SuppressWarnings("unchecked")
    private void outInfoBolt(TopologyBuilder topologyBuilder, String name, String boltId) {

        OutInfoBolt outInfoBolt = new OutInfoBolt(name);
        String configMapJson = "{\n" +
                "    \"serverAddress\":\"192.168.1.191:8848\",\n" +
                "    \"nameSpace\":\"test\",\n" +
                "    \"group\":\"storm\",\n" +
                "    \"timeout\":\"5000\",\n" +
                "    \"dataId\":\"uniqueKeyConfig\",\n" +
                "    \"dataId2\":\"allUseMetrics\"\n" +
                "}";
        Map<String, String> config = JsonUtils.parseJsonToObj(configMapJson, Map.class);
        //outInfoBolt.setConfig(config);

        // 给自己取名字 and消费别人的名字（即数据来源）
        topologyBuilder.setBolt(boltId, outInfoBolt, 2).setNumTasks(2).allGrouping(CUSTOM_INT_SPOUT_ID);
    }

    private void customIntSpout(TopologyBuilder topologyBuilder) {
        CustomIntSpout customIntSpout = new CustomIntSpout();
        // 给自己取名字，spout是源头，后面要消费这个源头，得知道名字才行。
        topologyBuilder.setSpout(CUSTOM_INT_SPOUT_ID, customIntSpout, 1);
    }

}
