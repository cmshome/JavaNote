package com.lxk.design.pattern.filter;

import java.util.List;

/**
 * 为标准，条件（Criteria）创建一个接口。
 *
 * @author LiXuekai on 2020/7/23
 */
public interface Criteria {

    List<Person> meetCriteria(List<Person> persons);
}
