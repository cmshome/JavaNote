package com.lxk.storm.bolt.rich;

import com.lxk.storm.spout.SentenceSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.apache.storm.utils.Utils;

import static com.lxk.storm.constants.CustomConstants.*;

/**
 * 实现单词计数topology
 * spout ---> split bolt ---> count bolt --->  report bolt
 *
 * @author LiXuekai on 2020/9/14
 */
public class TestWordCountTopology {
    private static final String TOPOLOGY_NAME = "word-count-topology";

    public static void main(String[] args) {

        //1，创建了一个TopologyBuilder实例
        TopologyBuilder builder = new TopologyBuilder();

        //2，实例化spout
        spout(builder);

        //3，实例化bolt
        splitBolt(builder);
        countBolt(builder);
        reportBolt(builder);

        //4，提交
        submitTopology(builder);
    }

    /**
     * TopologyBuilder提供流式风格的API来定义topology组件之间的数据流
     */
    private static void spout(TopologyBuilder builder) {
        SentenceSpout spout = new SentenceSpout();

        //注册一个sentence spout，设置两个Executeor(线程)，默认一个
        builder.setSpout(SENTENCE_SPOUT_ID, spout, 1);
    }

    /**
     * spout -> bolt1 即 SentenceSpout --> SplitSentenceBolt
     */
    private static void splitBolt(TopologyBuilder builder) {
        SplitSentenceBolt splitBolt = new SplitSentenceBolt();

        //注册一个bolt并订阅sentence发射出的数据流，shuffleGrouping方法告诉Storm要将SentenceSpout发射的tuple随机均匀的分发给SplitSentenceBolt的实例
        //SplitSentenceBolt单词分割器设置4个Task，2个Executeor(线程)
        builder.setBolt(SPLIT_BOLT_ID, splitBolt, 1).setNumTasks(1).shuffleGrouping(SENTENCE_SPOUT_ID);
    }

    /**
     * spout -> bolt1 -> bolt2 即 SplitSentenceBolt --> WordCountBolt
     */
    private static void countBolt(TopologyBuilder builder) {
        WordCountBolt countBolt = new WordCountBolt();

        //fieldsGrouping将含有特定数据的tuple路由到特殊的bolt实例中
        //这里fieldsGrouping()方法保证所有“word”字段相同的tuple会被路由到同一个WordCountBolt实例中
        //WordCountBolt单词计数器设置4个Executeor(线程)
        builder.setBolt(COUNT_BOLT_ID, countBolt, 1).fieldsGrouping(SPLIT_BOLT_ID, new Fields("word"));
    }

    /**
     * spout -> bolt1 -> bolt2 -> bolt3
     * WordCountBolt --> ReportBolt
     */
    private static void reportBolt(TopologyBuilder builder) {
        ReportBolt reportBolt = new ReportBolt();

        //globalGrouping是把WordCountBolt发射的所有tuple路由到唯一的ReportBolt
        builder.setBolt(REPORT_BOLT_ID, reportBolt).globalGrouping(COUNT_BOLT_ID);
    }

    /**
     * 提交 topology（拓扑）
     */
    private static void submitTopology(TopologyBuilder builder) {
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
}
