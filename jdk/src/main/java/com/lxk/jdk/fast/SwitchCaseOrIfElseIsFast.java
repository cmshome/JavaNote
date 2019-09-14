package com.lxk.jdk.fast;


import com.lxk.bean.enums.Type;

/**
 * if else 和 switch case 以及 case使用枚举  的效率比较
 *
 * @author LiXuekai on 2019/6/21
 */
public class SwitchCaseOrIfElseIsFast {
    public static void main(String[] args) {
        test();
    }

    /**
     * switch case ： if else = 50.6 : 49.4
     * switch case 之 char：int：enum = 32.9：33.3：33.9
     */
    private static void test() {
        //9，为公平起见，都在第九个匹配
        int lxk = Type.JSON.ordinal();
        Type type = Type.JSON;
        char aChar = '9';
        while (true) {
            //testIfElse(lxk);
            testSwitchCaseEnum(type);
            testSwitchCaseInt(lxk);
            testSwitchCaseChar(aChar);
        }
    }

    private static void testSwitchCaseEnum(Type type) {
        switch (type) {
            case _INTEGER:
                sameFunction();
                break;
            case INTEGER:
                sameFunction();
                break;
            case _LONG:
                sameFunction();
                break;
            case LONG:
                sameFunction();
                break;
            case _DOUBLE:
                sameFunction();
                break;
            case DOUBLE:
                sameFunction();
                break;
            case _FLOAT:
                sameFunction();
                break;
            case FLOAT:
                sameFunction();
                break;
            case STRING:
                sameFunction();
                break;
            case JSON:
                sameFunction();
                break;
            case OBJECT:
                sameFunction();
                break;
            default:
                sameFunction();
        }

    }

    private static void sameFunction() {
        String lxk = "li xue kai";
    }

    private static void testSwitchCaseInt(int type) {
        switch (type) {
            case 0:
                sameFunction();
                break;
            case 1:
                sameFunction();
                break;
            case 2:
                sameFunction();
                break;
            case 3:
                sameFunction();
                break;
            case 4:
                sameFunction();
                break;
            case 5:
                sameFunction();
                break;
            case 6:
                sameFunction();
                break;
            case 7:
                sameFunction();
                break;
            case 8:
                sameFunction();
                break;
            case 9:
                sameFunction();
                break;
            case 10:
                sameFunction();
                break;
            default:
                sameFunction();
        }
    }

    private static void testSwitchCaseChar(char type) {
        switch (type) {
            case '0':
                sameFunction();
                break;
            case '1':
                sameFunction();
                break;
            case '2':
                sameFunction();
                break;
            case '3':
                sameFunction();
                break;
            case '4':
                sameFunction();
                break;
            case '5':
                sameFunction();
                break;
            case '6':
                sameFunction();
                break;
            case '7':
                sameFunction();
                break;
            case '8':
                sameFunction();
                break;
            case '9':
                sameFunction();
                break;
            case 's':
                sameFunction();
                break;
            default:
                sameFunction();
        }
    }

    private static void testIfElse(int lxk) {
        if (lxk == 0) {
            sameFunction();
        } else if (lxk == 1) {
            sameFunction();
        } else if (lxk == 2) {
            sameFunction();
        } else if (lxk == 3) {
            sameFunction();
        } else if (lxk == 4) {
            sameFunction();
        } else if (lxk == 5) {
            sameFunction();
        } else if (lxk == 6) {
            sameFunction();
        } else if (lxk == 7) {
            sameFunction();
        } else if (lxk == 8) {
            sameFunction();
        } else if (lxk == 9) {
            sameFunction();
        } else if (lxk == 10) {
            sameFunction();
        } else {
            sameFunction();
        }

    }
}
