package com.lxk.bean.extend;

import lombok.*;

/**
 * TCL牌子的电视
 *
 * @author LiXuekai on 2019/5/14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TCL extends TV {
    public String where;
    public String led;
}
