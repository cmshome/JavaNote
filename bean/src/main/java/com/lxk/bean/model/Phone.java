package com.lxk.bean.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 手机
 *
 * @author LiXuekai on 2019/11/19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

    private String name;
    /**
     * 品牌
     */
    private String brand;
}
