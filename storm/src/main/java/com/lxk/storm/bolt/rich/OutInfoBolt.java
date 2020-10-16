package com.lxk.storm.bolt.rich;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.lxk.storm.constants.NacosConstants;
import com.lxk.storm.service.NacosService;
import com.lxk.tool.JsonUtils;
import com.lxk.tool.PrintUtil;
import lombok.Setter;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

import java.util.Map;
import java.util.Set;

import static com.lxk.storm.constants.NacosConstants.*;

/**
 * 接收传的信息，打印
 *
 * @author LiXuekai on 2020/10/13
 */
public class OutInfoBolt extends BaseRichBolt {

    private String name = "default_field";
    @Setter
    public Map<String, String> config;


    public OutInfoBolt() {
    }

    public OutInfoBolt(String name) {
        this.name = name;
    }

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        if (!config.isEmpty()) {
            System.out.println("启动nacos相关");
            nacos();
        } else {
            System.out.println("config map is empty.........");
        }
    }

    private void nacos() {
        NacosService.initNacosServer(config.get(NacosConstants.SERVER_ADDRESS), config.get(NacosConstants.NAME_SPACE));

        String dataId = config.get(DATA_ID_2);
        String group = config.get(GROUP);
        int timeout = Integer.parseInt(config.get(TIMEOUT));

        String content = NacosService.get(dataId, group, timeout);
        parseContent(content);

        NacosService.addListener(dataId, group, this::parseContent);
    }

    private void parseContent(String content) {
        if (Strings.isNullOrEmpty(content)) {
            System.out.println("get no data from nacos");
            return;
        }

        Set<String> set = Sets.newHashSet();
        try {
            JSONArray objects = JSON.parseArray(content);
            objects.forEach(o -> {
                if (o != null) {
                    set.add(o.toString());
                }
            });
        } catch (Exception e) {
            System.out.println("解析使用的所有指标的时候出错 ");
        }
        System.out.println("config from nacos: " + JsonUtils.parseObjToJson(set));
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
        declarer.declare(new Fields(name));
    }
}
