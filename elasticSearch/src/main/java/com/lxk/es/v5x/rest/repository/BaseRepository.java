package com.lxk.es.v5x.rest.repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static com.lxk.es.v5x.rest.constants.ConfConstant.*;

/**
 * @author LiXuekai on 2020/9/17
 */
public class BaseRepository {
    /**
     * 存查询条件
     */
    private final JSONObject body = new JSONObject();
    /**
     * 查询的地址
     */
    @Getter
    @Setter
    private String address;

    /**
     * ES客户端
     */
    @Getter
    @Setter
    private RestClient restClient;

    /**
     * 此查询所配置的索引是否存在，理论上是存在的。
     */
    protected boolean indexExist = true;

    /**
     * 简单通用的查询，没有设置啥筛选条件的查询。
     */
    public JSONArray getJsonArray() throws IOException {
        JSONArray array = new JSONArray();
        if (!indexExist) {
            System.out.println("索引不存在:");
            return array;
        }
        body.put(MQ_ES_ENTITY, "{\"size\":100}");
        body.put(MQ_ES_ENDPOINT, address + "?scroll=10m");
        while (true) {
            String partStream = startScroll();
            JSONObject jsonObject = JSON.parseObject(partStream);
            JSONArray jsonArray1 = jsonObject.getJSONObject(HITS).getJSONArray(HITS);
            if (jsonArray1.isEmpty()) {
                stopScroll();
                break;
            }
            JSONObject object = JSON.parseObject(partStream);
            JSONArray jsonArray = object.getJSONObject(HITS).getJSONArray(HITS);
            jsonArray.stream().map(s -> (JSONObject) s).forEach(s -> array.add(s.getJSONObject(SOURCE))
            );
        }
        System.out.println("getJsonArray: " + array.size());
        return array;
    }

    public String startScroll() throws IOException {
        String endpoint = body.getString(MQ_ES_ENDPOINT);
        String rsp = getResult(endpoint, body.getString(MQ_ES_ENTITY));
        if (rsp != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(SCROLL_ID_P, JSON.parseObject(rsp).getString(SCROLL_ID));
            jsonObject.put(SCROLL, "1m");
            body.put(MQ_ES_ENTITY, jsonObject.toString());
            body.put(MQ_ES_ENDPOINT, "/_search/scroll");
        }
        return rsp;
    }

    public String stopScroll() throws IOException {
        JSONObject jsonObject = JSON.parseObject(body.getString(MQ_ES_ENTITY));
        String string = jsonObject.getString(SCROLL_ID_P);
        return getResult("/_search/scroll/" + string, null);
    }

    private String getResult(String endPoint, String entity) throws IOException {
        return getResult(endPoint, entity, Collections.emptyMap());
    }

    @SuppressWarnings("unchecked")
    private String getResult(String endPoint, String entity, Map param) throws IOException {
        Response response = restClient.performRequest(GET, endPoint, param, entity != null ?
                new NStringEntity(entity, ContentType.APPLICATION_JSON) : null);
        return EntityUtils.toString(response.getEntity());
    }
}
