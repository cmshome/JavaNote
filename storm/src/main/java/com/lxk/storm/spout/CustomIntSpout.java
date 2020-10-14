package com.lxk.storm.spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 自定义spout，一直产出数字
 *
 * @author LiXuekai on 2020/10/14
 */
public class CustomIntSpout extends BaseRichSpout {
    private SpoutOutputCollector collector;

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void nextTuple() {
        long timestampMs = System.currentTimeMillis();
        long second = timestampMs / 1000;

        DateTimeFormatter sf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZoneId zoneId = ZoneOffset.systemDefault();
        LocalDateTime now = LocalDateTime.ofInstant(Instant.ofEpochSecond(second), zoneId);
        String time = now.format(sf);
        int nextInt = new Random().nextInt(10);

        System.out.println("CustomIntSpout 产出数字：number: " + nextInt + "  time: " + time
                + "  timestamp: " + second + "  timestampMs: " + timestampMs);
        this.collector.emit(new Values(nextInt, time, second, timestampMs));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("CustomIntSpout sleep error");
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("number", "time", "timestamp", "timestampMs"));

    }
}
