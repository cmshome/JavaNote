package com.lxk.vertx.elastic.repository;

import io.vertx.core.json.JsonArray;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

/**
 * 查询统计索引的dao
 *
 * @author LiXuekai on 2019/9/9
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SummaryRepository extends AbstractRepository {
    private final static Logger LOGGER = LoggerFactory.getLogger(SummaryRepository.class);

    private String fromQueryString;
    private String toQueryString;


    public SummaryRepository(String address, RestClient restClient, String fromQueryString, String toQueryString) {
        super(address, restClient);
        this.fromQueryString = fromQueryString;
        this.toQueryString = toQueryString;
        LOGGER.info("init SummaryRepository");
    }

    /**
     * 查询要复制的数据
     */
    public JsonArray getFromJsonArray() throws IOException {
        return super.getJsonArray(fromQueryString);
    }

    /**
     * 查询要删除的数据
     */
    public JsonArray getToJsonArray() throws IOException {
        return super.getJsonArray(toQueryString);
    }

}
