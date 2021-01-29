package com.lxk.storm.kafka;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lxk.tool.JsonUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

/**
 * kafka 发数据
 *
 * @author LiXuekai on 2020/7/20
 */
public class KafkaProducerTest {
    private boolean xml = false;
    private boolean all = true;

    @Test
    public void producer() {
        String toTopic = "a_citic_test_lxk";
        produceOnce(toTopic);
    }

    @Test
    public void producerAll() {
        xml = true;
        String toTopic = "citic_test_lxk";
        produceOnce(toTopic);
    }

    @Test
    public void producerXml() {
        xml = true;
        all = false;
        String toTopic = "citic_test_lxk";
        produceOnce(toTopic);
    }

    private void produceOnce(String toTopic) {
        Properties conf = new Properties();
        conf.setProperty(BOOTSTRAP_SERVERS_CONFIG, "192.168.1.191:9092");
        conf.put(ACKS_CONFIG, "all");
        conf.put(KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        conf.put(VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(conf);
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String ttm1 = "0|请求交易-async-lvj|524931|8080|xxxxxx|{\"橘右京\":\"不知好歹\",\"amount\":\"100\",\"TT\":\"AB\",\"lxk\":\"帅\",\"cus\":\"123\"}|{\"RC\": \"SUCCESS\",\"return_msg\": \"OK\"}|6|1568101860|1568101860123|11|22";
        String ttm2 = "0|响应交易-async-lvj|524932|8080|xxxxxx|{\"amount\":\"100\",\"TT\":\"AC\",\"lxk\":\"帅\",\"cus\":\"123\"}|{\"RC\": \"SUCCESS\",\"return_msg\": \"lxk-for-copy\"}|12|1568101860|1568101860123|22|11";
        List<String> list = Lists.newArrayList(
                ttm1.replace("xxxxxx",format),
                ttm2.replace("xxxxxx",format)
                //"0|请求交易-async-lvj-3-4|52493|8080|lvj-ID-3-4|{\"amount\":\"100\",\"TT\":\"AB\",\"lxk\":\"帅\",\"cus\":\"123\"}|{\"RC\": \"SUCCESS\",\"return_msg\": \"OK\"}|22|1568101860|1568101860123|3|4",
                //"0|响应交易-async-lvj-3-4|52493|8080|lvj-ID-3-4|{\"amount\":\"100\",\"TT\":\"AC\",\"lxk\":\"帅\",\"cus\":\"123\"}|{\"RC\": \"SUCCESS\",\"return_msg\": \"OK\"}|11|1568101860|1568101860123|4|3"
        );
        if (!all) {
            list.clear();
        }
        //if (xml) {
        //    list.add("");
        //}

        for (String s : list) {
            ProducerRecord<String, String> record = new ProducerRecord<>(toTopic, "asd", s);
            try {
                //producer.send(record);
                producer.send(record).get();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    @Test
    public void ttt() {
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(format);
    }

    @Test
    public void always() throws InterruptedException {
        String toTopic1 = "a_citic_test_lxk";
        String toTopic2 = "citic_test_lxk_2";
        Random random = new Random();

        while (true) {
            TimeUnit.SECONDS.sleep(random.nextInt(4));
            produceOnce(toTopic1);
            //produceOnce(toTopic2);
            System.out.println("send 2........");
        }

    }


    @Test
    public void te() {
        Map<String, String> map = Maps.newHashMap();
        map.put("lxk", "");
        String s = JsonUtils.parseObjToFormatJson(map);
        System.out.println(s);
    }

}
