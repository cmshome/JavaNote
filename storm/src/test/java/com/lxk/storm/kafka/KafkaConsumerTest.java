package com.lxk.storm.kafka;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lxk.tool.JsonUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.junit.Test;

import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

/**
 * 消费数据
 *
 * @author LiXuekai on 2020/7/20
 */
public class KafkaConsumerTest {

    @Test
    @SuppressWarnings("unchecked")
    public void test() {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(conf());
        consumer.subscribe(Lists.newArrayList("ez_complete"));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                Set<TopicPartition> assignment = consumer.assignment();

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
                        System.out.println(topic + "  " + JsonUtils.parseObjToJson(treeMap));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            consumer.close();
        }

    }

    private static Properties conf() {
        Properties conf = new Properties();
        conf.setProperty(BOOTSTRAP_SERVERS_CONFIG, "192.168.1.191:9092");
        conf.setProperty(GROUP_ID_CONFIG, "lxk-zxc");
        conf.setProperty(ENABLE_AUTO_COMMIT_CONFIG, "true");
        conf.setProperty(AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        conf.setProperty(AUTO_OFFSET_RESET_CONFIG, "latest");
        conf.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        conf.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        return conf;
    }

    /**
     * 同时启动15个线程，每个线程都启动一个consumer去消费kafka的相同topic，看分区情况。
     * topic一共有15个分区，启动18个线程，18个消费者去消费。
     *
     * 分区情况：Thread-3  [1]
     * 分区情况：Thread-16  [12]
     * 分区情况：Thread-13  [2]
     * 分区情况：Thread-14  [7]
     * 分区情况：Thread-12  [14]
     * 分区情况：Thread-0  [6]
     * 分区情况：Thread-17  [8]
     * 分区情况：Thread-5  [10]
     * 分区情况：Thread-8  [4]
     * 分区情况：Thread-1  [9]
     * 分区情况：Thread-6  [5]
     * 分区情况：Thread-4  []
     * 分区情况：Thread-11  [13]
     * 分区情况：Thread-2  []
     * 分区情况：Thread-7  []
     * 分区情况：Thread-9  [3]
     * 分区情况：Thread-10  [0]
     * 分区情况：Thread-15  [11]
     */
    @Test
    public void assignment() throws InterruptedException {
        for (int i = 0; i < 18; i++) {
            int finalIndex = i;
            new Thread(()->{
                System.out.println(finalIndex + " this run................");
                consumer();
            }).start();
        }
        TimeUnit.SECONDS.sleep(100);
    }

    private void consumer() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " this run................");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(conf());
        consumer.subscribe(Lists.newArrayList("ez_complete"));

        consumer.poll(10);
        Set<TopicPartition> assignment = consumer.assignment();
        Set<Integer> set = assignment.stream().map(TopicPartition::partition).collect(Collectors.toSet());
        System.out.println("分区情况：" + name  + "  "+ JsonUtils.parseObjToJson(set));
    }

}
