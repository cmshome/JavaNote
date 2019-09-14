package com.lxk.bean.extend;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * TV
 *
 * @author LiXuekai on 2019/5/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Builder(toBuilder = true)
public class TV {
    private String id;
    private String name;
}
