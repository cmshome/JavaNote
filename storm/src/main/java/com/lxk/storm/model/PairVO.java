package com.lxk.storm.model;

import lombok.Data;

/**
 * @author LiXuekai on 2020/9/25
 */
@Data
class PairVO<k, v> {
    private k name;
    private v value;
}
