package com.lxk.websocket;

import com.lxk.websocket.factory.Factory;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.WebSocketFrame;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import static com.lxk.websocket.constant.Constants.*;

/**
 * client
 *
 * @author LiXuekai on 2019/9/16
 */
public class Client extends AbstractVerticle {

    private final static Logger LOGGER = LoggerFactory.getLogger(Client.class);


    @Override
    public void start(Future<Void> startFuture) throws Exception {
        LOGGER.info("client is starting...");
        super.start();
        connectServer();
        LOGGER.info("client start success...");
    }

    /**
     * 客户端连接服务端，创建 web socket 长链接。
     */
    private void connectServer() {
        try {
            LOGGER.info("httpClient send get request ...");
            HttpClient httpClient = Factory.getHttpClient(vertx);
            httpClient.websocket(PORT, HOST, PATH, webSocket -> webSocket.frameHandler(this::handleReturn));
            LOGGER.info("httpClient send get request over...");
        } catch (Exception e) {
            LOGGER.error("load info from server Exception...." + e.getMessage());
        }
    }

    /**
     * 处理从服务端拿到的信息
     */
    private void handleReturn(WebSocketFrame handler) {
        LOGGER.info("httpClient get info from webSocket and data is " + handler.textData());
    }
}
