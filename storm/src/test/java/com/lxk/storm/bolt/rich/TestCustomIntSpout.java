package com.lxk.storm.bolt.rich;

import com.lxk.storm.spout.CustomIntSpout;
import com.lxk.tool.JsonUtils;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.lxk.storm.constants.CustomConstants.CUSTOM_INT_SPOUT_ID;
import static com.lxk.storm.constants.CustomConstants.OUT_INFO_BOLT_ID;

/**
 * 测试自定义 int spout
 *
 * @author LiXuekai on 2020/10/14
 */
public class TestCustomIntSpout {
    private static final String TOPOLOGY_NAME = "custom-int-spout-topology";


    @Test
    public void test() throws InterruptedException {

        //1，创建了一个TopologyBuilder实例
        TopologyBuilder topologyBuilder = new TopologyBuilder();

        //1，实例化 kafka spout
        customIntSpout(topologyBuilder);

        //3，bolt 消费数据
        outInfoBolt(topologyBuilder);

        //4，提交
        submitTopology(topologyBuilder);
    }

    private void submitTopology(TopologyBuilder builder) throws InterruptedException {
        //Config类是一个HashMap<String,Object>的子类，用来配置topology运行时的行为
        Config config = new Config();
        //设置worker数量
        //config.setNumWorkers(2);
        LocalCluster cluster = new LocalCluster();

        //本地提交
        cluster.submitTopology(TOPOLOGY_NAME, config, builder.createTopology());

        TimeUnit.MINUTES.sleep(5);
        cluster.killTopology(TOPOLOGY_NAME);
        cluster.shutdown();
    }

    @SuppressWarnings("unchecked")
    private void outInfoBolt(TopologyBuilder topologyBuilder) {

        OutInfoBolt outInfoBolt = new OutInfoBolt("lxk");
        String configMapJson = "{\n" +
                "    \"serverAddress\":\"192.168.1.191:8848\",\n" +
                "    \"nameSpace\":\"test\",\n" +
                "    \"group\":\"storm\",\n" +
                "    \"timeout\":\"5000\",\n" +
                "    \"dataId\":\"uniqueKeyConfig\",\n" +
                "    \"dataId2\":\"allUseMetrics\"\n" +
                "}";
        Map<String, String> config = JsonUtils.parseJsonToObj(configMapJson, Map.class);
        outInfoBolt.setConfig(config);

        // 给自己取名字 and消费别人的名字（即数据来源）
        topologyBuilder.setBolt(OUT_INFO_BOLT_ID, outInfoBolt, 1).setNumTasks(1).shuffleGrouping(CUSTOM_INT_SPOUT_ID);
    }

    private void customIntSpout(TopologyBuilder topologyBuilder) {
        CustomIntSpout customIntSpout = new CustomIntSpout();
        // 给自己取名字，spout是源头，后面要消费这个源头，得知道名字才行。
        topologyBuilder.setSpout(CUSTOM_INT_SPOUT_ID, customIntSpout, 1);
    }

}
