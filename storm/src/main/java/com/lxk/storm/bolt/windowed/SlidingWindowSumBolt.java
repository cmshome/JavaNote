package com.lxk.storm.bolt.windowed;

import com.lxk.tool.JsonUtils;
import com.lxk.tool.PrintUtil;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseWindowedBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.apache.storm.windowing.TupleWindow;

import java.util.List;
import java.util.Map;

/**
 * @author LiXuekai on 2020/10/14
 */
public class SlidingWindowSumBolt extends BaseWindowedBolt {

    private int sum = 0;
    private OutputCollector collector;


    @Override
    public void prepare(Map topoConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void execute(TupleWindow inputWindow) {
        /*
         * The inputWindow gives a view of
         * (a) all the events in the window
         * (b) events that expired since last activation of the window
         * (c) events that newly arrived since last activation of the window
         */
        List<Tuple> tuples = inputWindow.get();
        List<Tuple> newTuples = inputWindow.getNew();
        List<Tuple> expiredTuples = inputWindow.getExpired();

        out("all", tuples);
        out("new", newTuples);
        out("expired", expiredTuples);
        PrintUtil.divideLine();

        for (Tuple tuple : newTuples) {
            sum += (int) tuple.getValue(0);
        }
        for (Tuple tuple : expiredTuples) {
            sum -= (int) tuple.getValue(0);
        }
        collector.emit(new Values(sum));
    }

    private void out(String key, List<Tuple> tuples) {
        System.out.println(key);
        tuples.forEach(s -> System.out.println(JsonUtils.parseObjToJson(s.getValues())));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("sum"));
    }

}
