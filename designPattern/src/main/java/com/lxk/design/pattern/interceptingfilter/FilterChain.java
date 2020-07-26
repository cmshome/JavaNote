package com.lxk.design.pattern.interceptingfilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiXuekai on 2020/7/26
 */
public class FilterChain {
    private List<Filter> filters = new ArrayList<>();
    private Target target;

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public void execute(String request) {
        for (Filter filter : filters) {
            filter.execute(request);
        }
        target.execute(request);
    }

    public void setTarget(Target target) {
        this.target = target;
    }
}
