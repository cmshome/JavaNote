package com.lxk.design.pattern.dataaccessobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiXuekai on 2020/7/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String name;
    private int rollNo;
}
