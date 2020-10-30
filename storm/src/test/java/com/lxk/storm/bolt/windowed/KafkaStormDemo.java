package com.lxk.storm.bolt.windowed;

import org.apache.kafka.common.utils.Utils;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.topology.base.BaseWindowedBolt.Duration;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author LiXuekai on 2020/10/13
 */
public class KafkaStormDemo {


    @Test
    public void test() {

        KafkaSpoutConfig.Builder<String, String> builder = KafkaSpoutConfig.builder("192.168.1.191:9092", "a_citic_test_lxk");
        builder.setProp("group.id", "KAFKA_STORM");
        KafkaSpoutConfig<String, String> kafkaSpoutConfig = builder.build();

        TopologyBuilder tBuilder = new TopologyBuilder();
        tBuilder.setSpout("WordCountFileSpout", new KafkaSpout<String, String>(kafkaSpoutConfig), 1);
        tBuilder.setBolt("readKafkaBolt", new ConsumeKafkaBolt().withWindow(new Duration(5, TimeUnit.SECONDS), new Duration(3, TimeUnit.SECONDS))).shuffleGrouping("WordCountFileSpout");


        Config config = new Config();
        config.put(Config.TOPOLOGY_MESSAGE_TIMEOUT_SECS, 240);
        config.setDebug(true);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("KafkaStormDemo", config, tBuilder.createTopology());
        Utils.sleep(10000);
        cluster.killTopology("KafkaStormDemo");
        cluster.shutdown();
    }

}
