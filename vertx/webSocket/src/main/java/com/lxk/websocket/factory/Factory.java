package com.lxk.websocket.factory;


import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;

/**
 * 工厂类
 *
 * @author LiXuekai on 2019/9/16
 */
public class Factory {

    /**
     * httpClient
     */
    private static volatile HttpClient httpClient;
    /**
     * httpServer
     */
    private static volatile HttpServer httpServer;


    private Factory() {
    }


    /**
     * 获得 httpClient ：一个 httpClient 就OK了，不能每次发请求就创建一个吧。
     */
    public static HttpClient getHttpClient(Vertx vertx) {
        if (httpClient == null) {
            synchronized (Factory.class) {
                if (httpClient == null) {
                    HttpClientOptions options = new HttpClientOptions();
                    options.setMaxWebsocketFrameSize(6553600);
                    httpClient = vertx.createHttpClient(options);
                }
            }
        }
        return httpClient;
    }

    /**
     * 获得 httpServer ：一个 httpServer 就OK了，不能每次发请求就创建一个吧。
     */
    public static HttpServer getHttpServer(Vertx vertx) {
        if (httpServer == null) {
            synchronized (Factory.class) {
                if (httpServer == null) {
                    HttpServerOptions httpServerOptions = new HttpServerOptions();
                    httpServerOptions.setMaxWebsocketFrameSize(1000000);
                    httpServer = vertx.createHttpServer(httpServerOptions);
                }
            }
        }
        return httpServer;
    }

}
