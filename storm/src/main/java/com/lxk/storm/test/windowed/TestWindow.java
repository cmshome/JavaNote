package com.lxk.storm.test.windowed;

import com.lxk.storm.bolt.rich.OutInfoBolt;
import com.lxk.storm.bolt.windowed.SlidingWindowSumBolt;
import com.lxk.storm.spout.CustomIntSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.topology.base.BaseWindowedBolt;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static com.lxk.storm.constants.CustomConstants.*;

/**
 * @author LiXuekai on 2020/9/27
 */
public class TestWindow {
    private static final String TOPOLOGY_NAME = "windowed-topology";


    @Test
    public void window() {
        TopologyBuilder topologyBuilder = new TopologyBuilder();

        spout(topologyBuilder);

        windowedBolt(topologyBuilder);
        outInfoBolt(topologyBuilder);

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

        try {
            TimeUnit.MINUTES.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cluster.killTopology(TOPOLOGY_NAME);
        cluster.shutdown();
    }

    private void spout(TopologyBuilder builder) {
        builder.setSpout(CUSTOM_INT_SPOUT_ID, new CustomIntSpout(), 1);

    }

    private void windowedBolt(TopologyBuilder builder) {
        BaseWindowedBolt windowedBolt = new SlidingWindowSumBolt()
                //windowLength , slidingInterval
                .withWindow(new BaseWindowedBolt.Duration(5, TimeUnit.SECONDS), new BaseWindowedBolt.Duration(3, TimeUnit.SECONDS))

                /*
                //通过withTimestampField指定tuple的某个字段作为这个tuple的timestamp
                .withTimestampField("timestamp")

                //输入流中最新的元组时间戳的最小值减去Lag值=watermark，用于指定触发watermark的的interval，默认为1秒
                //当watermark被触发的时候，tuple timestamp比watermark早的window将被计算
                .withWatermarkInterval(new BaseWindowedBolt.Duration(1, TimeUnit.SECONDS))

                //withLag用于处理乱序的数据，当接收到的tuple的timestamp小于等lastWaterMarkTs(`取这批watermark的最大值`)，则会被丢弃
                .withLag(new BaseWindowedBolt.Duration(5, TimeUnit.SECONDS))

                 */
                ;

        // 给自己取名字 and消费别人的名字（即数据来源）
        builder.setBolt(SLIDING_WINDOW_BOLT_ID, windowedBolt, 1).shuffleGrouping(CUSTOM_INT_SPOUT_ID);
    }

    private void outInfoBolt(TopologyBuilder topologyBuilder) {
        OutInfoBolt outInfoBolt = new OutInfoBolt();
        // 给自己取名字 and消费别人的名字（即数据来源）
        topologyBuilder.setBolt(OUT_INFO_BOLT_ID, outInfoBolt, 1).setNumTasks(1).shuffleGrouping(SLIDING_WINDOW_BOLT_ID);
    }
}
