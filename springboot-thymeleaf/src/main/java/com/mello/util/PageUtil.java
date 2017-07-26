package com.mello.util;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MelloChan on 2017/6/3.
 * 分页管理工具
 */
public class PageUtil {
    public static Map<String,Object>getPageMaps(HttpServletRequest request){
        String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
        String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
        Map<String,Object> params = new HashMap<>();
        params.put("page", page);
        params.put("rows", rows);
        return params;
    }

    private static PageInfo<?>getPageInfo(List<?>list){
       return new PageInfo<>(list);
    }

    public static JSONObject getPageJson(List<?>list){
        PageInfo<?>pageInfo=getPageInfo(list);
        JSONObject data=new JSONObject();
        data.put("rows", list);
        data.put("total", pageInfo.getPages());//总页数
        data.put("records",pageInfo.getTotal());//查询出的总记录数
        return data;
    }
}
