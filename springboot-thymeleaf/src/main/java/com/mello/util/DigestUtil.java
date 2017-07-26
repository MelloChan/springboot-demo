package com.mello.util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 加密算法工具类
 */
public class DigestUtil {
    private DigestUtil(){}
    /**
     * MD5加密原文密码再通过BASE64编码成字符串
     * @param psw 原密码
     * @return 加密后的字符串
     */
    public static String digestMD5(String psw) {
        MessageDigest md;
        String pswMD5="";
        try {
            //将原字符串采用MD5加密成byte[]
            md = MessageDigest.getInstance("MD5");
            byte[] bbs = md.digest(psw.getBytes());
            //采用Base64算法将byte[]编码成字符串
            BASE64Encoder base64 = new BASE64Encoder();
            pswMD5=base64.encode(bbs);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return pswMD5;
    }

    /**
     * SHA-1加密字符串
     * @param list token、timestamp、随机数
     * @return 返回通过SHA-1加密后的字符串
     */
    static String digestSHA1(List<String> list) {
        MessageDigest md;
        String signature=list.get(0)+list.get(1)+list.get(2);
        try {
            md=MessageDigest.getInstance("SHA-1");
            byte[] bbs=md.digest(signature.getBytes());
            StringBuilder sb=new StringBuilder();
            //将比特数组转换成十六进制字符串
            for (byte bb : bbs) {
                int a = bb;
                if (0 > a) a += 256;
                if (16 > a) sb.append("0");
                sb.append(Integer.toHexString(a));
            }
            signature=sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return signature;
    }

    /**
     * Base64位加密
     * @param string 需要加密的字符串
     * @return 返回加密后的字符串
     */
    public static String digestBase64(String string) {
        BASE64Encoder base64 = new BASE64Encoder();
        return base64.encode(string.getBytes());
    }
}
