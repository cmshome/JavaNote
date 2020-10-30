package com.lxk.storm.spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.lxk.storm.constants.KafkaConstants.ALL_FIELD_NAME;

/**
 * 模仿kafka发出来的数据，5个k v 对儿
 *
 * @author LiXuekai on 2020/10/30
 */
public class FakeKafkaSpout extends BaseRichSpout {
    private SpoutOutputCollector collector;

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void nextTuple() {
        this.collector.emit(new Values("topic", "partition", "offset", "key", "value"));
        System.out.println("fake kafka spout 造数 一次。。。。");
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            System.out.println("FakeKafkaSpout sleep error");
        }
    }

    /**
     * 发出去的顺序也和实际 kafka spout 发出的一样。
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields(ALL_FIELD_NAME));
    }
}
