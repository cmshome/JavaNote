package com.lxk.vertx.elastic.main;

import com.lxk.tool.JsonUtils;
import com.lxk.vertx.elastic.factory.ElasticSearchRestClientFactory;
import com.lxk.vertx.elastic.module.Config;
import com.lxk.vertx.elastic.module.Repository;
import com.lxk.vertx.elastic.repository.SummaryRepository;
import com.google.common.base.Strings;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import static com.lxk.vertx.elastic.constants.ConfConstant.*;


/**
 * 操作ES的main
 *
 * @author lxk
 */
public class Main extends AbstractVerticle {

    private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static ZoneId zoneId = ZoneOffset.systemDefault();

    /**
     * 存储配置信息的对象
     */
    private static Config config;
    private static Repository repository;
    private static long[] fromTimeArray;
    private static long[] toTimeArray;


    @Override
    public void start(Future<Void> startFuture) throws Exception {
        LOGGER.info("elastic tool is starting...");
        super.start();
        try {
            initConfig();
        } catch (Exception e) {
            LOGGER.error("elastic tool 模块初始化异常，{}", e.getMessage());
            close();
            return;
        }
        blockRun();
        LOGGER.info("elastic tool start success...");
    }

    /**
     * 初始化模块的配置信息，初始化查询es的dao，初始化所有的流信息。
     */
    private void initConfig() throws Exception {
        initConfigInfo();
        initTimeArray();
        initRepository();
    }

    /**
     * 获取配置信息
     */
    @SuppressWarnings("unchecked")
    private void initConfigInfo() {
        LOGGER.info("init elastic tool config info from config json file starting...");
        JsonObject conf = config();
        config = Config.builder()
                .esHost(conf.getString("HOST_NAME"))
                .esPort(conf.getString("ES_PORT"))
                .summaryIndexName(conf.getString("summaryIndexName"))
                .fromTime(conf.getString("fromTime"))
                .toTime(conf.getString("toTime"))
                .streamIds(conf.getJsonArray("streamIds").getList())
                .startTimeKey(conf.getString("startTimeKey"))
                .endTimeKey(conf.getString("endTimeKey"))
                .statisticalKey(conf.getString("statisticalKey"))
                .build();
        LOGGER.info("init elastic tool config info from config json file success : " + JsonUtils.parseObjToJson(config));
    }

    /**
     * 初始化2个时间数组
     */
    private void initTimeArray() throws Exception {
        long[] toArray = getTimeArray(config.getToTime());
        if (toArray == null) {
            LOGGER.error("配置的toTime有问题： toTime：" + config.getToTime() + " indexName：" + config.getSummaryIndexName());
            throw new Exception();
        }
        toTimeArray = toArray;
        long[] fromArray = getTimeArray(config.getFromTime());
        if (fromArray == null) {
            LOGGER.error("配置的fromTime有问题：fromTime：" + config.getFromTime() + " indexName：" + config.getSummaryIndexName());
            throw new Exception();
        }
        fromTimeArray = fromArray;
    }

    /**
     * 将配置的一组开始结束时间 【2019-09-06-09:00:00 2019-09-06-11:00:00】转换成秒的时间的数组
     *
     * @param time time
     * @return [time1, time2]
     */
    private long[] getTimeArray(String time) {
        String[] timeArray = time.split(" ");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String indexName = null;
        try {
            //索引名称和时间要一致的判断 2019-07
            String s0 = timeArray[0];
            String s1 = timeArray[1];
            indexName = config.getSummaryIndexName();
            String indexDate = indexName.split("_")[1];
            String fromDate = s0.substring(0, 7);
            String toDate = s1.substring(0, 7);
            if (!indexDate.equalsIgnoreCase(fromDate) || !indexDate.equalsIgnoreCase(toDate)) {
                throw new Exception("索引名称和配置的时间不一致。。。");
            }

            Date from = simpleDateFormat.parse(s0);
            Date to = simpleDateFormat.parse(s1);
            return new long[]{from.getTime() / 1000L, to.getTime() / 1000L};
        } catch (Exception e) {
            LOGGER.error("索引名称和配置的时间需要一致，转换时间异常 time is {} and index name is {}", time, indexName);
        }
        return null;
    }

    /**
     * 初始化 Repository 初始化查询ES相关的类
     */
    private void initRepository() {
        LOGGER.info("init repository starting...");
        RestClient restClient = ElasticSearchRestClientFactory.getRestClient(config.getEsHost(), Integer.parseInt(config.getEsPort()));
        //形如/analyzier_2019-09/_search
        String url = SPLIT + config.getSummaryIndexName() + SEARCH;
        String from = initQueryStringByTime(fromTimeArray);
        String to = initQueryStringByTime(toTimeArray);
        repository = Repository
                .builder()
                .summaryRepository(new SummaryRepository(url, restClient, from, to))
                .build();
        LOGGER.info("init repository success ");

    }

    /**
     * 初始化查询ES的语句
     *
     * @param timeArray timeArray
     * @return 查询ES的语句
     */
    private String initQueryStringByTime(long[] timeArray) {
        String statisticalKey = getStatisticalKey();
        String startTimeKey = config.getStartTimeKey();
        String statisticalKeyName = config.getStatisticalKey();
        return "{\"query\":{\"bool\":{\"must\":[{\"range\":{\"" + startTimeKey + "\":{\"gte\":\"" +
                timeArray[0] +
                "\",\"lte\":\"" +
                timeArray[1] +
                "\"}}},{\"terms\":{\"" + statisticalKeyName + "\":[" +
                statisticalKey +
                "]}}]}},\"size\":500}";
    }

    /**
     * 拼接查询语句中的流
     */
    private String getStatisticalKey() {
        StringBuilder stringBuilder = new StringBuilder();
        config.getStreamIds().forEach(s -> stringBuilder.append("\"").append(s).append("\","));
        String result = stringBuilder.toString();
        return result.substring(0, result.length() - 1);
    }

    /**
     * 启动一个阻塞队列，去做一些事情。
     */
    private void blockRun() {
        vertx.executeBlocking(block -> run(), res -> {
            if (res.failed()) {
                LOGGER.error("execute blocking failed", res.cause());
                close();
            }
        });
    }

    /**
     * 另一个线程去做更新ES的事情
     */
    private void run() {
        deleteDate();
        updateDate();
        close();
    }

    /**
     * 删除指定时间段段数据
     */
    private void deleteDate() {
        JsonArray toData = getToData();
        if (toData == null) {
            return;
        }
        String urlBase = SPLIT + config.getSummaryIndexName() + MESSAGE;
        for (Object one : toData) {
            JsonObject message = (JsonObject) one;
            String id = message.getString(ID);
            if (Strings.isNullOrEmpty(id)) {
                continue;
            }
            deleteOne(urlBase + SPLIT + id);
        }
        LOGGER.info("delete all over ...");
    }

    /**
     * 删除一个数据
     *
     * @param url URL
     */
    private void deleteOne(String url) {
        try {
            LOGGER.info("delete one ...");
            repository.getSummaryRepository().deleteOne(url);
        } catch (IOException e) {
            LOGGER.error("deleteOne error, url {}", url);
        }
    }

    /**
     * 更新查询到的符合条件的数据
     */
    private void updateDate() {
        JsonArray all = getFromData();
        if (all == null) {
            LOGGER.error("查询不到原始数据。。。");
            return;
        }
        int toTimeDay = getToTimeDay();
        for (Object one : all) {
            JsonObject message = (JsonObject) one;
            Long start = message.getLong(config.getStartTimeKey());
            Long end = message.getLong(config.getEndTimeKey());
            message.put(config.getStartTimeKey(), changeDay(start, toTimeDay));
            message.put(config.getEndTimeKey(), changeDay(end, toTimeDay));
            String id = (String) message.remove(ID);
            updateOne(message, id);
        }
        LOGGER.info("update all over ...");
    }

    /**
     * 修改复制的原始交易的时间为toTime的时间
     *
     * @param timeLong  秒数
     * @param toTimeDay 目标时间的天数
     * @return 修改过日的时间
     */
    private Long changeDay(Long timeLong, int toTimeDay) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timeLong), zoneId);
        LocalDateTime dateTime = localDateTime.withDayOfMonth(toTimeDay);
        return dateTime.atZone(zoneId).toEpochSecond();
    }

    /**
     * 获取to时间的天数
     */
    private int getToTimeDay() {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(toTimeArray[0]), zoneId);
        return localDateTime.getDayOfMonth();
    }

    /**
     * 更新一个交易
     */
    private void updateOne(JsonObject message, String id) {
        try {
            LOGGER.info("update one ...");
            String url = SPLIT + config.getSummaryIndexName() + MESSAGE + SPLIT + id + System.currentTimeMillis();
            HttpEntity entity = new NStringEntity(message.toString(), ContentType.APPLICATION_JSON);
            repository.getSummaryRepository().updateOne(url, entity);
        } catch (Exception e) {
            LOGGER.error("updateOne error, id {}", id);
        }
    }

    /**
     * 查询符合条件的数据
     */
    private JsonArray getFromData() {
        try {
            return repository.getSummaryRepository().getFromJsonArray();
        } catch (IOException e) {
            LOGGER.error("repository.getSummaryRepository().getFromJsonArray() error ", e.getMessage());
        }
        return null;
    }

    /**
     * 查询符合条件的数据
     */
    private JsonArray getToData() {
        try {
            return repository.getSummaryRepository().getToJsonArray();
        } catch (IOException e) {
            LOGGER.error("repository.getSummaryRepository().getToJsonArray() error ", e.getMessage());
        }
        return null;
    }

    /**
     * 关闭vertx
     */
    private void close() {
        try {
            stop();
            vertx.close();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
