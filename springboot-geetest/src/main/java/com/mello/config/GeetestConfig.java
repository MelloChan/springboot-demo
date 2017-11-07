package com.mello.config;

/**
 * 配置文件
 * Created by MelloChan on 2017/11/7.
 */
public class GeetestConfig {
    // 填入自己的captcha_id和private_key
    private static final String geetest_id = "48a6ebac4ebc6642d68c217fca33eb4d";
    private static final String geetest_key = "4f1c085290bec5afdc54df73535fc361";
    private static final boolean newfailback = true;

    public static String getGeetest_id() {
        return geetest_id;
    }

    public static  String getGeetest_key() {
        return geetest_key;
    }

    public static boolean isNewfailback() {
        return newfailback;
    }
}
