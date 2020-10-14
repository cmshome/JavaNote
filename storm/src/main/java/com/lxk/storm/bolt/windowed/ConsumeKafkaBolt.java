package com.lxk.storm.bolt.windowed;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseWindowedBolt;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.windowing.TupleWindow;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author LiXuekai on 2020/10/13
 */
public class ConsumeKafkaBolt extends BaseWindowedBolt {
    private FileWriter fileWriter = null;
    private OutputCollector collector = null;
    private Map<String, Integer> counters;
    private Map<String, Integer> countern;


    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {//不失败的话只执行一次
        this.collector = outputCollector;
        this.counters = new HashMap<String, Integer>();
        this.countern = new HashMap<String, Integer>();
        try {
            fileWriter = new FileWriter("/Users/fang/Downloads/test/"+ UUID.randomUUID());
            System.out.println("------******prepare ok");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void execute(TupleWindow inputWindow) {
        //按条件循环执行
        System.out.println("------******execute");
        countern.clear();
        //Your business logic
        for(Tuple tuple: inputWindow.get()) {
            // do the windowing computation
            for (String wd: tuple.getString(4).split(" |,")){
                if(!counters.containsKey(wd)){
                    counters.put(wd, 1);
                }else {
                    Integer c = counters.get(wd) +1;
                    counters.put(wd, c);
                }
                if(!countern.containsKey(wd)){
                    countern.put(wd, 1);
                }else {
                    Integer c = countern.get(wd) +1;
                    countern.put(wd, c);
                }
            }
            try {
                fileWriter.write(counters.toString().replace("=",":")+" --Total\n");
                fileWriter.write(countern.toString().replace("=",":")+" --window\n");
                fileWriter.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //end your codes
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer){
    }
}
