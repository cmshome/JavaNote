package com.lxk.storm.bolt.rich;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

/**
 * 接收传的信息，打印
 *
 * @author LiXuekai on 2020/10/13
 */
public class OutInfoBolt extends BaseRichBolt {

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {

    }

    @Override
    public void execute(Tuple input) {
        try {
            // [topic, partition, offset, key, value]
            String string = input.getString(4);
            System.out.println(string);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
