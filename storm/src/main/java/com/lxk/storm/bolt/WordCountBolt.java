package com.lxk.storm.bolt;

import com.lxk.storm.model.RepeatEvent;
import org.apache.storm.shade.com.google.common.collect.Maps;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.TreeMap;

/**
 * 单词计数器
 * 订阅 split sentence bolt的输出流，实现单词计数，并发送当前计数给下一个bolt
 *
 * @author LiXuekai on 2020/9/14
 */
public class WordCountBolt extends BaseRichBolt {
    /**
     *
     */
    private OutputCollector collector;
    /**
     * 存储单词和对应的计数
     * 不可序列化对象需在prepare中实例化
     */
    private Map<String, Long> counts = null;


    /**
     * 大部分实例变量通常是在prepare()中进行实例化，这个设计模式是由topology的部署方式决定的
     * 因为在部署拓扑时,组件spout和bolt是在网络上发送的序列化的实例变量。
     * 如果spout或bolt有任何non-serializable实例变量在序列化之前被实例化(例如,在构造函数中创建)
     * 会抛出NotSerializableException并且拓扑将无法发布。
     * 本例中因为HashMap 是可序列化的,所以可以安全地在构造函数中实例化。
     * 但是，通常情况下最好是在构造函数中对基本数据类型和可序列化的对象进行复制和实例化
     * 而在prepare()方法中对不可序列化的对象进行实例化。
     */
    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        // TODO Auto-generated method stub
        this.collector = collector;
        this.counts = Maps.newTreeMap();
    }

    /**
     * 在execute()方法中,我们查找的收到的单词的计数(如果不存在，初始化为0)
     * 然后增加计数并存储,发出一个新的词和当前计数组成的二元组。
     * 发射计数作为流允许拓扑的其他bolt订阅和执行额外的处理。
     */
    @SuppressWarnings("unchecked")
    @Override
    public void execute(Tuple input) {
        // TODO Auto-generated method stub

        String word = input.getStringByField("word");
        RepeatEvent event = (RepeatEvent) input.getValueByField("repeatEvent");
        TreeMap<String, Integer> dataMap = (TreeMap<String, Integer>) input.getValueByField("dataMap");
        Long count = this.counts.get(word);
        if (count == null) {
            //如果不存在，初始化为0
            count = 0L;
        }
        //增加计数
        count++;
        String name = Thread.currentThread().getName();
        System.out.println("二、word count bolt：" + word + " " + count + ", current thread name:" + name);
        //存储计数
        this.counts.put(word, count);
        this.collector.emit(new Values(word, count));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // TODO Auto-generated method stub
        //声明一个输出流，其中tuple包括了单词和对应的计数，向后发射
        //其他bolt可以订阅这个数据流进一步处理
        declarer.declare(new Fields("word", "count"));
    }
}
