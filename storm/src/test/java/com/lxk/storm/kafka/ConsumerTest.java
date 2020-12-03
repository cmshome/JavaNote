package com.lxk.storm.kafka;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lxk.tool.JsonUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;

import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

/**
 * 消费数据
 *
 * @author LiXuekai on 2020/7/20
 */
public class ConsumerTest {

    @Test
    @SuppressWarnings("unchecked")
    public void test() {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(conf());
        consumer.subscribe(Lists.newArrayList("a_3.x_detail"));

        try {
            while (true){
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    String key = record.key();
                    int partition = record.partition();
                    String topic = record.topic();
                    long offset = record.offset();
                    String value = record.value();
                    Map map = JsonUtils.parseJsonToObj(value, Map.class);
                    if (map != null) {
                        TreeMap treeMap = Maps.newTreeMap();
                        map.forEach(treeMap::put);
                        System.out.println(topic + "  " +JsonUtils.parseObjToJson(treeMap));
                    }
                }
            }
        } catch (Exception e){
            System.out.println(e);
        } finally {
            consumer.close();
        }

    }

    private static Properties conf() {
        Properties conf = new Properties();
        conf.setProperty(BOOTSTRAP_SERVERS_CONFIG, "192.168.1.191:9092");
        conf.setProperty(GROUP_ID_CONFIG, "lxk");
        conf.setProperty(ENABLE_AUTO_COMMIT_CONFIG, "true");
        conf.setProperty(AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        conf.setProperty(AUTO_OFFSET_RESET_CONFIG, "latest");
        conf.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        conf.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        return conf;
    }
}
