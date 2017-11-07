package com.mello.api;

import com.mello.config.GeetestConfig;
import com.mello.sdk.GeetestLib;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import java.util.HashMap;

/**
 * 适用于前后端分离方案
 * Created by MelloChan on 2017/11/7.
 */
@Controller
@ResponseBody
public class GeetestApi {
    @GetMapping("/gt/init")
    public String geetestStart(HttpServletRequest request) throws IOException {
        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(), GeetestConfig.isNewfailback());
        String resStr = "{}";
        String userid = "test";

        //自定义参数,选择性添加
        HashMap<String, String> param = new HashMap<>();
        param.put("user_id", userid); //用户id
//        param.put("client_type", "web"); //用户访问类型
//        param.put("ip_address", "127.0.0.1"); //用户ip

        //进行验证预处理
        int gtServerStatus = gtSdk.preProcess(param);
        //session 设置服务器状态
        request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
        //session 设置用户id
        request.getSession().setAttribute("userid", userid);
        resStr = gtSdk.getResponseStr();
        return resStr;
    }

    @PostMapping("/gt/validate")
    public String validGtCode(HttpServletRequest request) {
        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(), GeetestConfig.isNewfailback());
        String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
        String validate = request.getParameter(GeetestLib.fn_geetest_validate);
        String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);
        //从session中获取 server状态 与 userid
        int gt_server_status_code = (int) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
        String userid = (String) request.getSession().getAttribute("userid");
        //自定义参数,选择性添加
        HashMap<String, String> param = new HashMap<>();
        param.put("user_id", userid); //用户id
//        param.put("client_type", "web"); //用户访问类型
//        param.put("ip_address", "127.0.0.1"); //用户ip
        int gtResult = 0;
        if (gt_server_status_code == 1) {
            //状态正常
            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证
            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
            System.out.println(gtResult);
        }
        JSONObject data = null;
        if (gtResult == 1) {
            // 验证成功
            data = new JSONObject();
            try {
                data.put("status", "success");
                data.put("version", gtSdk.getVersionInfo());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            // 验证失败
            data = new JSONObject();
            try {
                data.put("status", "fail");
                data.put("version", gtSdk.getVersionInfo());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return data.toString();
    }
}
