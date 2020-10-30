package com.lxk.storm.bolt.rich;

import com.google.common.collect.Lists;
import com.lxk.storm.bolt.BaseBoltTest;
import com.lxk.storm.constants.CustomConstants;
import com.lxk.storm.spout.FakeKafkaSpout;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author LiXuekai on 2020/10/30
 */
public class TestReplicatingSelectorBolt extends BaseBoltTest {

    @Before
    public void init() {
        TOPOLOGY_NAME = "replicate-selector--topology";
    }

    @Test
    public void test() {

        fakeKafkaSpout();

        replicate();
        outInfoBolt1();
        outInfoBolt2();

        submitTopology();
    }

    private void fakeKafkaSpout() {
        FakeKafkaSpout fakeKafkaSpout = new FakeKafkaSpout();
        builder.setSpout(CustomConstants.FAKE_KAFKA_SPOUT_ID, fakeKafkaSpout, 1);
    }

    private void replicate() {
        List<String> streamIds = Lists.newArrayList(CustomConstants.STREAM_ID_1, CustomConstants.STREAM_ID_2);
        ReplicatingSelectorBolt replicatingSelectorBolt = new ReplicatingSelectorBolt(streamIds);
        builder.setBolt(CustomConstants.REPLICATE_BOLT, replicatingSelectorBolt, 1).shuffleGrouping(CustomConstants.FAKE_KAFKA_SPOUT_ID);
    }

    private void outInfoBolt2() {
        OutInfoBolt outInfoBolt = new OutInfoBolt("打印信息的bolt名称-1");
        builder.setBolt("id-1", outInfoBolt, 2).shuffleGrouping(CustomConstants.REPLICATE_BOLT, CustomConstants.STREAM_ID_1);
    }

    private void outInfoBolt1() {
        OutInfoBolt outInfoBolt = new OutInfoBolt("打印信息的bolt名称-2");
        builder.setBolt("id-2", outInfoBolt, 2).shuffleGrouping(CustomConstants.REPLICATE_BOLT, CustomConstants.STREAM_ID_2);
    }

}
