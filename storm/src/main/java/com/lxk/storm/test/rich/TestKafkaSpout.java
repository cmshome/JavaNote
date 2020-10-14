package com.lxk.storm.test.rich;

import com.lxk.storm.bolt.rich.OutInfoBolt;
import org.apache.kafka.common.utils.Utils;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.TopologyBuilder;
import org.junit.Test;

/**
 * 就测试一下从kafka消费数据，然后在bolt里面可以看到数据。
 *
 * @author LiXuekai on 2020/10/13
 */
public class TestKafkaSpout {
    private static final String TOPOLOGY_NAME = "kafka-spout-topology";

    @Test
    public void test() {

        //1，创建了一个TopologyBuilder实例
        TopologyBuilder topologyBuilder = new TopologyBuilder();

        //1，实例化 kafka spout
        kafkaSpout(topologyBuilder);

        //3，bolt 消费数据
        outInfoBolt(topologyBuilder);

        //4，提交
        submitTopology(topologyBuilder);
    }

    private void submitTopology(TopologyBuilder builder) {
        //Config类是一个HashMap<String,Object>的子类，用来配置topology运行时的行为
        Config config = new Config();
        //设置worker数量
        //config.setNumWorkers(2);
        LocalCluster cluster = new LocalCluster();

        //本地提交
        cluster.submitTopology(TOPOLOGY_NAME, config, builder.createTopology());

        Utils.sleep(10000);
        cluster.killTopology(TOPOLOGY_NAME);
        cluster.shutdown();
    }

    private void outInfoBolt(TopologyBuilder topologyBuilder) {
        OutInfoBolt outInfoBolt = new OutInfoBolt();
        topologyBuilder.setBolt("out-info-bolt-id", outInfoBolt, 1).setNumTasks(1).shuffleGrouping("kafka-info-spout-id");

    }

    private void kafkaSpout(TopologyBuilder topologyBuilder) {
        KafkaSpout<String, String> kafkaSpout = new KafkaSpout<>(initKafkaSpoutConfig());
        topologyBuilder.setSpout("kafka-info-spout-id", kafkaSpout, 1);
    }

    private KafkaSpoutConfig<String, String> initKafkaSpoutConfig() {
        KafkaSpoutConfig.Builder<String, String> builder = KafkaSpoutConfig.builder("192.168.1.191:9092", "a_citic_test_lxk");
        builder.setProp("group.id", "KAFKA_STORM");
        return builder.build();
    }
}
