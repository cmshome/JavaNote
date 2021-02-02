package com.lxk.websocket;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lxk.websocket.factory.Factory;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

import static com.lxk.websocket.constant.Constants.PATH;
import static com.lxk.websocket.constant.Constants.PORT;

/**
 * server
 *
 * @author LiXuekai on 2019/9/16
 */
public class Server extends AbstractVerticle {

    private final static Logger LOGGER = LoggerFactory.getLogger(Server.class);


    @Override
    public void start(Future<Void> startFuture) throws Exception {
        LOGGER.info("server is starting...");
        super.start();
        HttpServer server = Factory.getHttpServer(vertx);
        websocketHandler(server);
        Router router = Router.router(vertx);
        serverListenLast(server, router);
        LOGGER.info("server start success...");
    }

    /**
     * 在设置完handler之后，最后设置监听事件
     *
     * @param server server
     * @param router router
     */
    private void serverListenLast(HttpServer server, Router router) {
        server.requestHandler(router::accept).listen(PORT);
    }

    /**
     * 处理其他模块通过websocket来ezSC模块获取一些配置信息的请求
     *
     * @param server HttpServer
     */
    private void websocketHandler(HttpServer server) {
        LOGGER.info("web socket Handler init");
        server.websocketHandler(websocket -> {
            String path = websocket.path();
            LOGGER.info("server get http request, path is " + path);
            switch (path) {
                case PATH:
                    ThreadFactory monitorThreadFactory = new ThreadFactoryBuilder().setNameFormat("File-Config-Center-Monitor-Thread-Pool-%d").build();
                    ScheduledExecutorService monitorSchedule = new ScheduledThreadPoolExecutor(1, monitorThreadFactory, new ThreadPoolExecutor.AbortPolicy());
                    monitorSchedule.scheduleWithFixedDelay(() -> writeData(websocket), 15, 15, TimeUnit.SECONDS);
                    break;
                default:
                    LOGGER.info("websocket.reject()");
                    websocket.reject();
                    break;
            }
        });
        LOGGER.info("websocketHandler init success");
    }

    /**
     * 触发请求之后，服务端定时的往 websocket 里面写信息
     *
     * @param websocket websocket
     */
    private void writeData(ServerWebSocket websocket) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter sf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = now.format(sf);
        websocket.writeFinalTextFrame("server data is writing... server now is " + date);
    }
}
