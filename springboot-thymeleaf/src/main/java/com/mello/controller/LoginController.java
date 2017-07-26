package com.mello.controller;

import com.alibaba.fastjson.JSONObject;
import com.mello.domain.Admin;
import com.mello.service.AdminService;
import com.mello.util.DigestUtil;
import com.mello.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/1.
 * 登录控制器
 */
@RestController
@RequestMapping("/infoOS")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AdminService adminService;

    @Autowired
    public LoginController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public Map<String, Object> login(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(DigestUtil.digestBase64(password));
        if (!StringUtil.isNull(username) && !StringUtil.isNull(password) && adminService.find(admin)) {
            request.getSession().setAttribute("admin", admin);
            map.put("result", "1");
        } else {
            map.put("result", "0");
        }
        return map;
    }

    @RequestMapping(value = {"/signOut"}, method = RequestMethod.GET)
    public Map<String, Object> signOut(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Map<String, Object> map = new HashMap<>();
        if (session != null) {
            session.invalidate();
            map.put("msg", "success");
        } else {
            map.put("msg", "fail");
        }
        return map;
    }
}
