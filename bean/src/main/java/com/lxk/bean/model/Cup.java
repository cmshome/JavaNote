package com.lxk.bean.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 杯子
 *
 * @author LiXuekai on 2018/12/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cup {
    private String color;
    private String size;
    private String prise;
    private Cup self;
}
