package com.lxk.design.pattern.filter;

import java.util.List;

/**
 * 为标准，条件（Criteria）创建一个接口。
 *
 * @author LiXuekai on 2020/7/23
 */
public interface Criteria {

    /**
     * 满足 标准
     *
     * @param persons list
     * @return 满足条件的data
     */
    List<Person> meetCriteria(List<Person> persons);
}
