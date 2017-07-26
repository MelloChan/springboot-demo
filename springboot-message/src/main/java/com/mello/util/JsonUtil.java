package com.mello.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/5/20.
 * JSON转换器
 */
public class JsonUtil {
    public static JsonObject streamToJson(HttpServletRequest request) throws IOException {
        System.out.println(new InputStreamReader(request.getInputStream()).getEncoding());
        BufferedReader br=new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line;
        StringBuilder buffer=new StringBuilder();
        while ((line=br.readLine())!=null)
            buffer.append(line);
        br.close();
        return (JsonObject) new JsonParser().parse(buffer.toString());
    }
}
