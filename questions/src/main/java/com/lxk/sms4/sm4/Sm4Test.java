package com.lxk.sms4.sm4;

import com.lxk.sms4.utils.sm4.SM4Utils;
import org.junit.Test;

/**
 * @author LiXuekai on 2020/9/4
 */
public class Sm4Test {

    private static final String data = "0|  mr_noresponse    |92|93|name1|{\"json\":\"jsjsj{\\\"key\\\": \\\"value\\\"}\\\"\\\"\\\"\\\"\\\"\\\"sj\",\"k1\": \"k1\",\"k2\": \"k2\", \"amount\":\"00000001234.023\",\"amount1\":\"1000.5678\",\"amount12bit1\":\"000000000010\",\"amount12bit2\":\"000110000010\",\"SQL\":\"SELECT * FROM ezsonar from 123456789012345678901234567890 hx_zs.zs_sky_kkxx a left join dm_gy_swjg b on a.sjgsdq= b.SWJG_DM  from hx_zs.zs_sky_kkxx a left join dm_gy_swjg b on a.sjgsdq= b.SWJG_DM from hx_zs.zs_sky_kkxx a left join dm_gy_swjg b on a.sjgsdq= b.SWJG_DM  from hx_zs.zs_sky_kkxx a left join dm_gy_swjg b on a.sjgsdq= b.SWJG_DM   \",\"TT\":\"   White9TT   \",\"S1\":\"White8ref1\",\"S2\":\"White8ref2\",\"S3\":\"White8ref3\",\"S4\":\"White8ref4\",\"S5\":\"White8ref5\",\"TIATRCD\":\"12345678k\",\"TIARSYS\":\"网银\",\"ISACNO\":\"20180607180305\",\"ISACNM\":\"张三三\",\"ISGOAC\":\"2018060718040809\",\"ISOANM\":\"李四思\",\"ISSBST\":\"借款撒\"}|{\"RC\":\"White9RC\",\"S1\":\"White8_1\",\"S2\":\"White8_2\",\"S3\":\"White8_3\",\"S4\":\"White8_4\",\"S5\":\"White8_5\",\"probe_st\":\"noresponse\"}|11|1578806040|1578806040000|2257650043|2257650300";

    @Test
    public void sm4() {
        System.out.println("CBC模式加密");

        SM4Utils sm4 = new SM4Utils();
        sm4.secretKey = "E76E9B4E0245BC56FCE4E29B208C6A50";
        sm4.hexString = true;

        String plainText = data;
        sm4.iv = "30303030303030303030303030303030";
        String cipherText = sm4.encryptData_CBC(plainText);
        System.out.println("加密密文: \n" + cipherText);
        System.out.println("");

        plainText = sm4.decryptData_CBC(cipherText);
        System.out.println("解密明文: \n" + plainText);

        System.out.println("\n" + plainText.equals(data));
        System.out.println();


        int length = data.getBytes().length;
        int length1 = cipherText.getBytes().length;
        System.out.println(length + "  " +length1 );
    }
}
