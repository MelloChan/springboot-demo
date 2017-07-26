package com.mello.util;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by MelloChan on 2017/6/3.
 * JSON数据工具
 */
public class JsonUtil {
    public static JSONObject getResult(int index){
        JSONObject result=new JSONObject();
        if(index>0){
            result.put("message","操作成功!");
            result.put("flag",true);
        }else{
            result.put("message","操作失败!");
            result.put("flag",false);
        }
        return result;
    }
}
