package com.lxk.storm.bolt.rich;

import com.lxk.tool.JsonUtils;
import com.lxk.tool.PrintUtil;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
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
            //fromKafkaInfo(input);
            commonOutInfo(input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void commonOutInfo(Tuple input) {
        Fields fields = input.getFields();
        if (fields == null || fields.size() == 0) {
            System.out.println("empty field");
            return;
        }

        fields.forEach(field -> {
            Object value = input.getValueByField(field);
            System.out.println("OutInfoBolt 输出信息： field : " + field + "     value : " + JsonUtils.parseObjToJson(value));
        });

        PrintUtil.divideLine();
    }

    /**
     * 消费kafka数据的时候，测试数字4的由来。
     * 此方法不通用
     */
    private void fromKafkaInfo(Tuple input) {
        // [topic, partition, offset, key, value]
        String string = input.getString(4);
        System.out.println(string);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
