package com.lxk.es.v5x.rest;

import com.alibaba.fastjson.JSONArray;
import com.lxk.es.v5x.rest.factory.ElasticSearchRestClientFactory;
import com.lxk.es.v5x.rest.repository.BaseRepository;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author LiXuekai on 2020/9/17
 */
public class Main {
    private static final String HOST = "192.168.1.191";
    private static final int PORT = 9200;
    private static final BaseRepository BASE_REPOSITORY = new BaseRepository();
    private RestClient restClient;


    @Before
    public void before() {
        restClient = ElasticSearchRestClientFactory.getRestClient(HOST, PORT);

        String address = "/xxx_config/location/_search";
        BASE_REPOSITORY.setAddress(address);
        BASE_REPOSITORY.setRestClient(restClient);

    }

    @Test
    public void index() throws IOException {
        Response response = restClient.performRequest("HEAD", "alarms_2020-09");
        boolean exist = response.getStatusLine().getReasonPhrase().equals("OK");
        System.out.println(exist);
    }

    @Test
    public void queryTest() throws IOException {
        JSONArray jsonArray = BASE_REPOSITORY.getJsonArray();
        jsonArray.forEach(ss->{
            System.out.println(ss.toString());
        });
    }
}
