package com.lxk.storm.bolt;

import org.apache.storm.shade.com.google.common.collect.Maps;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseWindowedBolt;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.windowing.TupleWindow;

import java.util.List;
import java.util.Map;

/**
 * @author LiXuekai on 2020/9/27
 */
public class WindowCountBolt extends BaseWindowedBolt {

    /**
     *
     */
    private OutputCollector collector;
    /**
     * 存储单词和对应的计数
     * 不可序列化对象需在prepare中实例化
     */
    private Map<String, Long> counts = Maps.newHashMap();


    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;

    }

    @Override
    public void execute(TupleWindow inputWindow) {
        List<Tuple> tupleList = inputWindow.get();
        if (tupleList == null || tupleList.isEmpty()) {
            return;
        }
        long now = System.currentTimeMillis() / 1000;
        for (Tuple tuple : tupleList) {
            String field = tuple.getStringByField("word");
            Long aLong = counts.get(field);
            collector.ack(tuple);
            if (aLong == null || aLong < 2){
                continue;
            }

        }


    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // TODO Auto-generated method stub
        //这里是末端bolt，不需要发射数据流，这里无需定义
    }

}
