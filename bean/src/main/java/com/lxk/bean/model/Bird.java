package com.lxk.bean.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 鸟
 *
 * @author LiXuekai on 2018/10/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Bird implements Serializable {
    private static final long serialVersionUID = 1L;

    @JSONField(ordinal = 6, name = "dog1属性", jsonDirect = true)
    private String dog1;

    @JSONField(ordinal = 5, name = "dog2属性")
    private String dog2;

    /**
     * ordinal，默认值为0，不用设置啦。
     */
    @JSONField(ordinal = 4, name = "生产日期", format = "yyyy年MM月dd日 HH时mm分ss秒")
    private Date birthday;

    @JSONField(ordinal = 3, name = "颜色")
    private String color;

    @JSONField(ordinal = 2, name = "体型大小")
    private String size;

    /**
     * 使用 @Builder.Default 使得在使用builder的时候，支持默认值。不使用这个注解的话，使用builder的时候，就无法设置默认值了。
     */
    @JSONField(ordinal = 1, name = "年龄")
    @Builder.Default
    private int age = 100;
    /**
     * 反序列化false，那么在反序列化的时候，就不会把json的值转给对象的这个属性。
     */
    @JSONField(name = "名称", deserialize = false)
    private String name;
    /**
     * 不序列化此属性字段，那么在转json的时候，就不会在json中出现
     */
    @JSONField(ordinal = 7, name = "不序列化的属性字段", serialize = false)
    private String deserialize;


}
