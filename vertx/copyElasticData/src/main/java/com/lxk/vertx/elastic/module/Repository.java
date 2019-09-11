package com.lxk.vertx.elastic.module;

import com.lxk.vertx.elastic.repository.SummaryRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 所有查询的dao
 *
 * @author LiXuekai on 2019/7/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Repository {
    private SummaryRepository summaryRepository;
}
