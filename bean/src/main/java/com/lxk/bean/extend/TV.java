package com.lxk.bean.extend;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TV
 *
 * @author LiXuekai on 2019/5/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TV {
    private String id;
    private String name;
}
