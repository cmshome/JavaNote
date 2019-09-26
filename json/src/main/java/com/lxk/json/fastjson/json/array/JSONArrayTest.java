package com.lxk.json.fastjson.json.array;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lxk.bean.model.Bank;
import org.junit.Test;

import java.util.List;

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

    /**
     * json 对象list 字符串转 对象list
     */
    @Test
    public void parseObjectArray() {
        String detail = "[{\"countAll\":934,\"date\":\"20181018\",\"branchId\":\"1501\",\"amount\":42610089.86,\"count\":291,\"branchName\":\"重庆银行股份有限公司七星岗支行\"},{\"countAll\":1454,\"date\":\"20181018\",\"branchId\":\"2303\",\"amount\":33947568,\"count\":378,\"branchName\":\"重庆银行股份有限公司洋河支行\"},{\"countAll\":865,\"date\":\"20181018\",\"branchId\":\"2601\",\"amount\":70513457.33,\"count\":196,\"branchName\":\"重庆银行股份有限公司建新北路支行\"},{\"countAll\":3524,\"date\":\"20181018\",\"branchId\":\"3701\",\"amount\":103094203.07,\"count\":807,\"branchName\":\"重庆银行股份有限公司李家沱支行\"},{\"countAll\":2230,\"date\":\"20181018\",\"branchId\":\"5601\",\"amount\":61229363.11,\"count\":623,\"branchName\":\"重庆银行股份有限公司江津支行\"},{\"countAll\":985,\"date\":\"20181018\",\"branchId\":\"6702\",\"amount\":30560133.87,\"count\":234,\"branchName\":\"重庆银行股份有限公司秀山五岳广场支行\"}]";
        List<Bank> all = JSON.parseArray(detail, Bank.class);
        System.out.println(all.toString());
    }
}
