package com.lxk.jdk.doc;


import com.lxk.bean.model.CodeBlockModel;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 如何写好程序代码注释
 * 代码既不是给自己写的，但也是给自己写的。
 *
 * @author lxk on 2016/11/8
 */
public class JavaDocTest {
    public static void main(String[] args) {
        String order = "1128";
        if (!order.equals("1128")) {
            testTypeCast();
            simpleTest();
        } else {
            testCodeBlock();
        }
    }

    /**
     * 测试代码块的执行，顺便测试Java注释之[@link]和[@see]
     * <p>
     * <p>
     * {@link com.lxk.bean.model.CodeBlockModel}
     * 不顶头写，也可以，{@link CodeBlockModel}
     * 不顶头写，也可以，{@link CodeBlockModel#name}
     * 不顶头写，也可以，{@link CodeBlockModel#CodeBlockModel}
     *
     * @see com.lxk.bean.model.CodeBlockModel
     * @see CodeBlockModel
     * @see CodeBlockModel#name
     * @see CodeBlockModel#CodeBlockModel
     * 不顶头写，就无效了，@see com.lxk.model.CodeBlockModel
     */
    private static void testCodeBlock() {
        CodeBlockModel codeBlockModel1 = new CodeBlockModel();
        CodeBlockModel codeBlockModel2 = new CodeBlockModel("cms", "18", "student");

    }

    private static void testTypeCast() {
        float scale = 34.236323F;
        DecimalFormat fnum = new DecimalFormat("##0.00");
        long num = 1;
        float result = ((float) num) / (1024L * 1024L * 1024L);
        String dd = fnum.format(result);
        System.out.println(dd);

        //BigDecimal b = new BigDecimal(result);
        //float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        //System.out.println(f1);
    }

    private static void simpleTest() {
        String aw = "";
        String[] aaa = aw.split(",");
        System.out.println(aaa.length);
        System.out.println("------------------");

        System.out.println(1 / 10);
        String s = "bbbaaa";
        System.out.println(s.replace("*", ".*"));
        List<String> ss = null;
        String qw = -1 > 0 ? "true" : "false";

        System.out.println(qw);
    }
}
