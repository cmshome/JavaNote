package com.lxk.storm.bolt.rich;

import com.lxk.storm.bolt.BaseBoltTest;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.TopologyBuilder;
import org.junit.Before;
import org.junit.Test;

import static com.lxk.storm.constants.CustomConstants.KAFKA_INFO_SPOUT_ID;
import static com.lxk.storm.constants.CustomConstants.OUT_INFO_BOLT_ID;

/**
 * 就测试一下从kafka消费数据，然后在bolt里面可以看到数据。
 *
 * @author LiXuekai on 2020/10/13
 */
public class TestKafkaSpout extends BaseBoltTest {


    @Before
    public void init() {
        TOPOLOGY_NAME = "kafka-spout-topology";
    }

    @Test
    public void test() {

        //1，实例化 kafka spout
        kafkaSpout(builder);

        //3，bolt 消费数据
        outInfoBolt(builder);

        //4，提交
        submitTopology();
    }


    private void outInfoBolt(TopologyBuilder topologyBuilder) {
        OutInfoBolt outInfoBolt = new OutInfoBolt();
        // 给自己取名字 and消费别人的名字（即数据来源）
        topologyBuilder.setBolt(OUT_INFO_BOLT_ID, outInfoBolt, 1).setNumTasks(1).shuffleGrouping(KAFKA_INFO_SPOUT_ID);
    }

    private void kafkaSpout(TopologyBuilder topologyBuilder) {
        KafkaSpout<String, String> kafkaSpout = new KafkaSpout<>(initKafkaSpoutConfig());
        // 给自己取名字，spout是源头，后面要消费这个源头，得知道名字才行。
        topologyBuilder.setSpout(KAFKA_INFO_SPOUT_ID, kafkaSpout, 1);
    }

    private KafkaSpoutConfig<String, String> initKafkaSpoutConfig() {
        KafkaSpoutConfig.Builder<String, String> builder = KafkaSpoutConfig.builder("192.168.1.191:9092", "a_citic_test_lxk");
        builder.setProp("group.id", "KAFKA_STORM");
        return builder.build();
    }
}
