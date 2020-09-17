package com.lxk.es.v5x.client;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.URI;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * transport 一般访问的是9300端口
 * https://blog.csdn.net/qq_27093465/article/details/108603091
 *
 * @author LiXuekai on 2020/9/17
 */
public class Main {
    private static final int DEFAULT_PORT = 9300;
    private Client client;

    @Before
    public void init() throws Exception {
        // 存在的es集群名称
        String elasticCluster = "XXX";
        String elasticUrl = "http://192.168.1.191:9200/";
        Settings settings = Settings.builder()
                .put("client.transport.sniff", false)
                .put("cluster.name", elasticCluster).build();
        PreBuiltTransportClient preBuiltTransportClient = new PreBuiltTransportClient(settings);

        String[] clients = elasticUrl.split("\\|");
        for (String c : clients) {
            if (Strings.isNullOrEmpty(c)) {
                continue;
            }
            final URI uri = new URI(elasticUrl);
            final String host = uri.getHost();
            int port = uri.getPort();
            preBuiltTransportClient.addTransportAddress(
                    new InetSocketTransportAddress(InetAddress.getByName(host), port == 9200 ? DEFAULT_PORT : port)
            );
        }

        client = preBuiltTransportClient;
    }


    @Test
    public void test() {
        //设置搜索条件
        QueryBuilder qb = termQuery("name", "测2");
        // 存在的es索引名称
        SearchResponse scrollResp = client.prepareSearch("XXXX_config").setTypes("stream")
                .setScroll(new TimeValue(30000))
                .setQuery(qb)
                .setSize(2000)
                .get();
        int count = 0;

        do {
            for (SearchHit hit : scrollResp.getHits().getHits()) {
                System.out.println(hit.getScore() + " --> " + hit.getSourceAsString());
                count++;
            }

            System.out.println("scrollid:  " + scrollResp.getScrollId());

            //设置sroll id
            scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
            System.out.println();


        } while (scrollResp.getHits().getHits().length != 0);


        System.out.println(count);
    }
}
