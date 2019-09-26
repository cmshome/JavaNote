package com.lxk.bean.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiXuekai on 2019/9/18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
    /**
     * 日期
     */
    private String date;
    /**
     * 分支ID
     */
    private String branchId;
    /**
     * 分支名称
     */
    private String branchName;
    /**
     * 卡的类型
     */
    private String cardType;
    /**
     * 金额
     */
    private Double amount;
    /**
     * 累计的总数
     */
    private Long countAll;
    /**
     * 一年的总数
     */
    private Long count;
}
