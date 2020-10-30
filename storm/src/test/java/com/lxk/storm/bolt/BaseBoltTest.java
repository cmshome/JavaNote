package com.lxk.storm.bolt;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.junit.Before;

import java.util.concurrent.TimeUnit;

/**
 * @author LiXuekai on 2020/10/30
 */
public class BaseBoltTest {
    /**
     * topology name
     */
    protected static String TOPOLOGY_NAME = "default-topology-name";
    /**
     * 创建了一个TopologyBuilder实例
     */
    protected TopologyBuilder builder = new TopologyBuilder();


    @Before
    public void init() {

    }


    public void submitTopology() {
        try {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
