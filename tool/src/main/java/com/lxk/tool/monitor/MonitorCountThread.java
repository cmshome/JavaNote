package com.lxk.tool.monitor;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.lxk.tool.JsonUtils;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 主要监控数量变化
 *
 * @author LiXuekai on 2020/12/15
 */
public class MonitorCountThread implements Runnable {

    /**
     * <key, 对应的count>
     */
    private final Map<String, AtomicLong> map = Maps.newConcurrentMap();

    /**
     * 监控的name
     */
    private String name = "lxk";


    /**
     * 为了安全，无参构造也给带上。
     */
    public MonitorCountThread() {
    }

    /**
     * 带name的构造
     *
     * @param name name
     */
    public MonitorCountThread(String name) {
        this.name = name;
    }

    /**
     * 监控key的count
     *
     * @param key key
     */
    public void getAndAdd(String key) {
        if (Strings.isNullOrEmpty(key)) {
            return;
        }
        AtomicLong atomicLong = map.get(key);
        if (atomicLong == null) {
            atomicLong = new AtomicLong();
        }
        atomicLong.getAndIncrement();
        map.put(key, atomicLong);
    }

    /**
     * run
     */
    @Override
    public void run() {
        mapLog();
    }

    /**
     * 打印日志
     */
    private void mapLog() {
        TreeMap<String, Long> treeMap = Maps.newTreeMap();
        map.forEach((k, v) -> treeMap.put(k, v.get()));
        System.out.println(name + " monitor info is " + JsonUtils.parseObjToJson(treeMap));
        map.clear();
    }
}
