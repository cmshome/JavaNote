package com.lxk.json.fastjson.json.array;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

    @Test
    public void parse2ObjectArray() {
        String json = "{\"errCode\":null,\"message\":null,\"data\":{\"tenantId\":\"2b308777268849d7b2c2c29cdc5d3896\",\"realname\":\"测试\",\"username\":null,\"tenantName\":null,\"contacts\":null,\"email\":\"testaa@uyun.com\",\"mobile\":\"15665656565\",\"passwd\":null,\"industryId\":null,\"companySizeId\":null,\"userId\":\"2b308777268849d7b2c2c29cdc5d3896\",\"thirdPartyUserId\":null,\"userType\":null,\"apiKeys\":[{\"id\":\"e4cf86aa6274499aa763d45d4c4d6c7d\",\"tenantId\":\"2b308777268849d7b2c2c29cdc5d3896\",\"key\":\"15708061da104f3688ab9ab36097a23f\",\"addTime\":1574144298000,\"userId\":\"2b308777268849d7b2c2c29cdc5d3896\",\"secretKey\":\"e75043f153854606bac067c08514a00e1c3d06df\",\"hashValue\":null}],\"addTime\":1574144298000,\"office\":null,\"removed\":null,\"payType\":\"charge\",\"language\":\"zh_CN\",\"timezone\":null,\"timezoneId\":null,\"licenseType\":1,\"description\":null,\"inviteCode\":null,\"qq\":null,\"weixin\":null,\"site\":null,\"skin\":null,\"root\":true,\"status\":\"1\",\"products\":[{\"productId\":\"1e42b7a151ea434d800319190257f6a4\",\"productNum\":\"0001\",\"productName\":\"0001\",\"description\":\"测试第三方登录\",\"productUrl\":\"http://192.168.11.106:8080/thirdlogin\",\"enabled\":1,\"removed\":0,\"orderby\":1,\"addTime\":1574142818000,\"relevancyTime\":null,\"role\":0,\"productType\":null,\"productPicUrl\":null,\"othersFlag\":null,\"version\":null,\"headEnabled\":null,\"logo\":null,\"logoName\":null,\"sessionInvalidCallbackUrl\":null},{\"productId\":\"6ef8a9b74dcc4596a13368309253748d\",\"productNum\":\"134567899\",\"productName\":\"测试第三方登录\",\"description\":\"测试第三方登录\",\"productUrl\":\"http://192.168.11.106:8080/thirdlogin/th/getUserDetail\",\"enabled\":1,\"removed\":0,\"orderby\":1,\"addTime\":1574143902000,\"relevancyTime\":null,\"role\":1,\"productType\":null,\"productPicUrl\":null,\"othersFlag\":null,\"version\":null,\"headEnabled\":null,\"logo\":null,\"logoName\":null,\"sessionInvalidCallbackUrl\":null}],\"userExcess\":null,\"userNo\":\"test\",\"userCount\":null,\"tenantRoleIds\":null,\"imagePath\":\"/tenant/userimages/default.png\",\"astrictExpiry\":null,\"expiryDate\":null,\"passwordExpiryDate\":1574144298481,\"crossDomainStatus\":null,\"topUserId\":null,\"properties\":null},\"mode\":\"offline\",\"language\":\"zh_CN\"}";

        JSONObject parse = (JSONObject)JSON.parse(json);
        JSONObject data = (JSONObject)parse.get("data");
        Object userNo = data.get("userNo");


    }
}
