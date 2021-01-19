package com.lxk.json.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lxk.tool.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author LiXuekai on 2021/1/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cup {
    private String name;
    private String color;
    private Map<String, String> map;

    public static String getArrayJson() {
        Map<String, String> map = Maps.newHashMap();
        map.put("@type", "white");
        map.put("type", "white");

        List<Cup> list = Lists.newArrayList(
                new Cup("@type", "white", Maps.newHashMap(map)),
                new Cup("@type", "black", Maps.newHashMap(map))
        );
        return JsonUtils.parseObjToJson(list);
    }

    @Override
    public String toString() {
        return JsonUtils.parseObjToJson(this);
    }
}
