package com.mello.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 */
public class StringUtil {

    /**
     * 判断字符串是否为null、“ ”、“null”
     *
     * @param obj 判断对象
     * @return 是否为空
     */
    public static boolean isNull(String obj) {
        boolean flag = false;
        if (obj == null) {
            flag = true;
        } else if (obj.trim().equals("")) {
            flag = true;
        } else if (obj.trim().toLowerCase().equals("null")) {
            flag = true;
        }

        return flag;
    }

    /**
     *
     * @param list 字符串列表
     * @return 是否为空
     */
    public static boolean isNull(List<String> list) {
        boolean flag =true;
        for (String string:list){
            flag=StringUtil.isNull(string);
        }
        return flag;
    }

    /**
     * 正则验证是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[+-]?[0-9]+[0-9]*(\\.[0-9]+)?");
        Matcher match = pattern.matcher(str);

        return match.matches();
    }

    /**
     * 将一个长整数转换位字节数组(8个字节)，b[0]存储高位字符，大端
     *
     * @param l 长整数
     * @return 代表长整数的字节数组
     */
    public static byte[] longToBytes(long l) {
        byte[] b = new byte[8];
        b[0] = (byte) (l >>> 56);
        b[1] = (byte) (l >>> 48);
        b[2] = (byte) (l >>> 40);
        b[3] = (byte) (l >>> 32);
        b[4] = (byte) (l >>> 24);
        b[5] = (byte) (l >>> 16);
        b[6] = (byte) (l >>> 8);
        b[7] = (byte) (l);
        return b;
    }
}
