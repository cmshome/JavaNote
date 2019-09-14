package com.lxk.bean.extend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 小米TV
 *
 * @author LiXuekai on 2019/5/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class XiaoMiTV extends TV {
    private long price;
    private String color;

    public XiaoMiTV(String id, String name, long price, String color) {
        super(id, name);
        this.price = price;
        this.color = color;
    }
}
