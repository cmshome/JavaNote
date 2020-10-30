package com.lxk.storm.bolt.rich;

import com.lxk.tool.JsonUtils;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static com.lxk.storm.constants.KafkaConstants.*;


/**
 * 复制选择器bolt:
 */
public class ReplicatingSelectorBolt extends BaseRichBolt {
    private static final Logger logger = LoggerFactory.getLogger(ReplicatingSelectorBolt.class);

    private OutputCollector collector;
    private List<String> streamIds;


    public ReplicatingSelectorBolt() {
    }

    public ReplicatingSelectorBolt(List<String> streamIds) {
        this.streamIds = streamIds;
        logger.info("ReplicatingSelectorBolt init streamIds is {}", JsonUtils.parseObjToJson(streamIds));
    }

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;

    }

    @Override
    public void execute(Tuple input) {
        if (streamIds == null || streamIds.isEmpty()) {
            logger.error("ReplicatingSelectorBolt no streamIds found.");
            return;
        }
        streamIds.forEach(streamId -> collector.emit(streamId, input.getValues()));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields(TOPIC, PARTITION, OFFSET, KEY, VALUE));
    }

}
