package com.lxk.design.pattern.interceptingfilter;

/**
 * @author LiXuekai on 2020/7/26
 */
public class Client {
    private FilterManager filterManager;

    public void setFilterManager(FilterManager filterManager) {
        this.filterManager = filterManager;
    }

    public void sendRequest(String request) {
        filterManager.filterRequest(request);
    }
}
