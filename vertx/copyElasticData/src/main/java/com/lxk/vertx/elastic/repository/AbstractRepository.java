package com.lxk.vertx.elastic.repository;

import com.google.common.base.Strings;
import com.lxk.vertx.elastic.constants.ConfConstant;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;


/**
 * dao 抽象
 *
 * @author LiXuekai on 2019/7/10
 */
@Data
@NoArgsConstructor
abstract class AbstractRepository {

    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractRepository.class);

    /**
     * 存查询条件
     */
    protected JsonObject body = new JsonObject();
    /**
     * 查询的地址
     */
    protected String address;
    /**
     * ES客户端
     */
    private RestClient restClient;


    AbstractRepository(String address, RestClient restClient) {
        this.address = address;
        this.restClient = restClient;
    }

    String startScroll() throws IOException {
        String endpoint = body.getString(ConfConstant.MQ_ES_ENDPOINT);
        String rsp = getResult(endpoint, body.getString(ConfConstant.MQ_ES_ENTITY));
        if (rsp != null) {
            body.put(ConfConstant.MQ_ES_ENTITY, new JsonObject().put(ConfConstant.SCROLL_ID_P, new JsonObject(rsp).getString(ConfConstant.SCROLL_ID)).put(ConfConstant.SCROLL, "1m").toString());
            body.put(ConfConstant.MQ_ES_ENDPOINT, "/_search/scroll");
        }
        return rsp;
    }

    String stopScroll() throws IOException {
        return getResult("/_search/scroll/" + new JsonObject(body.getString(ConfConstant.MQ_ES_ENTITY)).getString(ConfConstant.SCROLL_ID_P), null);
    }

    private String getResult(String endPoint, String entity) throws IOException {
        return getResult(endPoint, entity, Collections.emptyMap());
    }

    @SuppressWarnings("unchecked")
    private String getResult(String endPoint, String entity, Map param) throws IOException {
        LOGGER.info("searching elastic search .......");
        Response response = restClient.performRequest(ConfConstant.GET, endPoint, param, entity != null ?
                new NStringEntity(entity, ContentType.APPLICATION_JSON) : null);
        return EntityUtils.toString(response.getEntity());
    }

    /**
     * 简单通用的查询，没有设置啥筛选条件的查询。
     */
    JsonArray getJsonArray(String searchString) throws IOException {
        JsonArray array = new JsonArray();
        String search = searchString;
        if (Strings.isNullOrEmpty(searchString)) {
            search = "{\"size\":100}";
        }
        body.put(ConfConstant.MQ_ES_ENTITY, search);
        body.put(ConfConstant.MQ_ES_ENDPOINT, address + "?scroll=1m");
        while (true) {
            String partStream = startScroll();
            if (new JsonObject(partStream).getJsonObject(ConfConstant.HITS).getJsonArray(ConfConstant.HITS).isEmpty()) {
                stopScroll();
                break;
            }
            JsonObject jsonObject = new JsonObject(partStream);
            JsonArray jsonArray = jsonObject.getJsonObject(ConfConstant.HITS).getJsonArray(ConfConstant.HITS);
            jsonArray.stream().map(s -> (JsonObject) s).forEach(s -> {
                        JsonObject json = s.getJsonObject(ConfConstant.SOURCE);
                        json.put(ConfConstant.ID, s.getString(ConfConstant.ID));
                        array.add(json);
                    }
            );
        }
        LOGGER.info("getJsonArray: " + array.size());
        return array;
    }

    /**
     * 向ES存一个数据
     *
     * @param url    请求地址
     * @param entity 存ES的数据信息
     * @throws IOException 异常
     */
    public void updateOne(String url, HttpEntity entity) throws IOException {
        restClient.performRequest(ConfConstant.PUT, url, Collections.emptyMap(), entity);
    }

    /**
     * 删除一条数据
     *
     * @param url 请求地址
     * @throws IOException 异常
     */
    public void deleteOne(String url) throws IOException {
        restClient.performRequest(ConfConstant.DELETE, url);
    }

}
