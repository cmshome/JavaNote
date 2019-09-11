package com.lxk.vertx.elastic.factory;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.RestClient;

import static org.elasticsearch.client.RestClientBuilder.DEFAULT_CONNECTION_REQUEST_TIMEOUT_MILLIS;

/**
 * ElasticSearch Rest Client Factory
 *
 * @author LiXuekai on 2019/7/8
 */
public class ElasticSearchRestClientFactory {

    private final static Logger LOGGER = LoggerFactory.getLogger(ElasticSearchRestClientFactory.class);

    /**
     * 超时时间设为5分钟
     */
    private static final int TIME_OUT = 5 * 60 * 1000;

    /**
     * ElasticSearch Rest Client
     */
    private static volatile RestClient restClient;

    private ElasticSearchRestClientFactory() {
    }

    /**
     * 单例模式获取连接ES客户端
     *
     * @param hostname es服务器地址，从配置文件获取
     * @param port     es服务的端口，从配置文件获取
     * @return restClient
     */
    public static RestClient getRestClient(final String hostname, final int port) {
        LOGGER.info("get elasticsearch rest client start");
        if (restClient == null) {
            synchronized (ElasticSearchRestClientFactory.class) {
                if (restClient == null) {
                    LOGGER.info("restClient is null, now create a elasticsearch rest client");
                    restClient = RestClient.builder(new HttpHost(hostname, port))
                            .setMaxRetryTimeoutMillis(TIME_OUT)
                            .setHttpClientConfigCallback(httpClientBuilder -> {
                                RequestConfig.Builder requestConfigBuilder = RequestConfig.custom()
                                        //超时时间5分钟
                                        .setConnectTimeout(TIME_OUT)
                                        //这就是Socket超时时间设置
                                        .setSocketTimeout(TIME_OUT)
                                        .setConnectionRequestTimeout(DEFAULT_CONNECTION_REQUEST_TIMEOUT_MILLIS);
                                httpClientBuilder.setDefaultRequestConfig(requestConfigBuilder.build());
                                return httpClientBuilder;
                            })
                            .build();
                    LOGGER.info("create elasticsearch rest client success");
                }
            }
        }
        LOGGER.info("get elasticsearch rest client over");
        return restClient;
    }
}
