package com.lxk.json.fastjson.json.array;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.Test;

/**
 * JSONArray test
 *
 * @author LiXuekai on 2019/9/24
 */
public class JSONArrayTest {


    /**
     * json 数组字符串转 JSONArray 对象
     */
    @Test
    public void parseArray() {
        String ageRangeSet = "[{\"35<\":\"35以上\"},{\"24-35\":\"24-35(含)\"},{\"18-24\":\"18-24(含)\"},{\"12-18\":\"12-18(含)\"},{\"<12\":\"0-12(含)\"}]";
        JSONArray jsonArray = JSON.parseArray(ageRangeSet);
        System.out.println(jsonArray.toString());
    }
}
