package com.lxk.design.pattern.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建一个类，在该类上应用标准
 *
 * @author LiXuekai on 2020/7/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String name;
    /**
     * 性别
     */
    private String gender;
    /**
     * 婚姻 状况
     */
    private String maritalStatus;
}
