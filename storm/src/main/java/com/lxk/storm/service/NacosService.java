package com.lxk.storm.service;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

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

    public static ConfigService getNacosServer(String serverAddress, String nameSpace) {
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

        return configService;
    }

    public static void pushConfig(String content, String dataId, String group) {
        try {
            configService.publishConfig(dataId, group, content);
        } catch (NacosException e) {
            logger.error("pushConfig error", e);
        }
    }


    public static String get(String dataId, String group) {
        try {
            String config = configService.getConfig(dataId, group, 5000);
            System.out.println("get from nacos ........" + config);
            return config;
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }
}
