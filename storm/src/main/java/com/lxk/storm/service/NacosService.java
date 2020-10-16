package com.lxk.storm.service;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * nacos service
 *
 * @author LiXuekai on 2020/10/15
 */
public class NacosService {
    private static final Logger logger = LoggerFactory.getLogger(NacosService.class);

    private static volatile ConfigService configService;


    private NacosService() {
    }

    public static void initNacosServer(String serverAddress, String nameSpace) {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddress);
        properties.put(PropertyKeyConst.NAMESPACE, nameSpace);
        if (configService == null) {
            synchronized (NacosService.class) {
                if (configService == null) {
                    try {
                        configService = NacosFactory.createConfigService(properties);
                        logger.info("init nacos configService success serverAddress: {} , nameSpace : {}", serverAddress, nameSpace);
                    } catch (NacosException e) {
                        logger.error("init nacos 配置中心失败。。。serverAddress: {} , nameSpace : {}", serverAddress, nameSpace, e);
                    }
                }
            }
        }
    }

    public static void pushConfig(String content, String dataId, String group) {
        try {
            configService.publishConfig(dataId, group, content);
        } catch (NacosException e) {
            logger.error("pushConfig error", e);
        }
    }

    public static String get(String dataId, String group) {
        return get(dataId, group, 5000);
    }

    public static String get(String dataId, String group, int timeout) {
        try {
            String config = configService.getConfig(dataId, group, timeout);
            System.out.println("get from nacos " + config);
            return config;
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addListener(String dataId, String group, CallBack callBack) {
        try {
            configService.addListener(dataId, group, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    callBack.callBack(configInfo);
                }
            });
        } catch (NacosException e) {
            logger.error("add listener error, dataId is {}, group is {}", dataId, group, e);
        }
    }
}
