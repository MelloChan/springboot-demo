package com.mello.controller;

import com.alibaba.fastjson.JSONObject;
import com.mello.domain.Admin;
import com.mello.service.AdminService;
import com.mello.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/2.
 * 用户管理控制器
 */
@Controller
@RequestMapping("/infoOS/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 管理员主界面
     *
     * @return 返回页面模板
     */
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String admin() {
        return "admin-resource";
    }

    /**
     * 获取管理员列表
     *
     * @param request  请求
     * @param response 响应
     */
    @RequestMapping(value = {"/findByAll"}, method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void findByAll(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = PageUtil.getPageMaps(request);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        params.put("username", username);
        params.put("password", password);
        List<Admin> adminList = adminService.findByAll(params);
        JSONObject data = PageUtil.getPageJson(adminList);
        ServletUtil.createSuccessResponse(200, data, response);
    }

    /**
     * 新添管理员信息
     *
     * @param request  请求
     * @param response 响应
     */
    @RequestMapping(value = {"/saveAdmin"}, method = RequestMethod.POST)
    @ResponseBody
    public void saveAdmin(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result;
        Admin admin = getAdmin(request);
        if (!ObjectUtil.isNull(admin)) {
            int index = adminService.saveAdmin(admin);
            result = JsonUtil.getResult(index);
        } else {
            result = new JSONObject();
            result.put("message", "数据不完全!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }

    /**
     * 更新管理员信息
     *
     * @param request  请求
     * @param response 响应
     */
    @RequestMapping(value = {"/updateAdmin"}, method = RequestMethod.POST)
    @ResponseBody
    public void updateAdmin(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result;
        Admin admin = getAdmin(request);
        if (!ObjectUtil.isNull(admin)) {
            admin.setId(Integer.parseInt(request.getParameter("id")));
            int index = adminService.updateAdmin(admin);
            result = JsonUtil.getResult(index);
        } else {
            result = new JSONObject();
            result.put("message", "数据不完全!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }

    /**
     * 删除管理员信息
     *
     * @param request  请求
     * @param response 响应
     */
    @RequestMapping(value = {"/deleteByIds"}, method = RequestMethod.POST)
    @ResponseBody
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) {
        String ids = request.getParameter("ids");
        int index = adminService.deleteByIds(ids.split(","));
        JSONObject result = JsonUtil.getResult(index);
        ServletUtil.createSuccessResponse(200, result, response);
    }

    /**
     * 组装用户数据
     *
     * @param request 获取参数
     * @return 返回用户类
     */
    private Admin getAdmin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Admin admin = null;
        if (!StringUtil.isNull(username) && !StringUtil.isNull(password)) {
            admin = new Admin(username, DigestUtil.digestBase64(password));
        }
        return admin;
    }

}
