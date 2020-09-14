package com.lxk.storm.bolt;

import org.apache.storm.shade.com.google.common.collect.Lists;
import org.apache.storm.shade.com.google.common.collect.Maps;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 报告生成器
 *
 * @author LiXuekai on 2020/9/14
 */
public class ReportBolt extends BaseRichBolt {
    /**
     * 保存单词和对应的计数
     */
    private Map<String, Long> counts = null;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        // TODO Auto-generated method stub
        this.counts = Maps.newHashMap();
    }

    @Override
    public void execute(Tuple input) {
        // TODO Auto-generated method stub
        String word = input.getStringByField("word");
        Long count = input.getLongByField("count");
        this.counts.put(word, count);
        //实时输出
        System.out.println("结果:" + this.counts);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // TODO Auto-generated method stub
        //这里是末端bolt，不需要发射数据流，这里无需定义
    }

    /**
     * cleanup是IBolt接口中定义
     * Storm在终止一个bolt之前会调用这个方法
     * 本例我们利用cleanup()方法在topology关闭时输出最终的计数结果
     * 通常情况下，cleanup()方法用来释放bolt占用的资源，如打开的文件句柄或数据库连接
     * 但是当Storm拓扑在一个集群上运行，IBolt.cleanup()方法不能保证执行（这里是开发模式，生产环境不要这样做）。
     */
    @Override
    public void cleanup(){
        System.out.println("---------- FINAL COUNTS -----------");

        List<String> keys = Lists.newArrayList();
        keys.addAll(this.counts.keySet());
        Collections.sort(keys);
        for(String key : keys){
            System.out.println(key + " : " + this.counts.get(key));
        }
        System.out.println("----------------------------");
    }
}
