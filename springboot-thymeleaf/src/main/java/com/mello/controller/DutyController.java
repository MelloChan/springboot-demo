package com.mello.controller;

import com.alibaba.fastjson.JSONObject;
import com.mello.domain.Duty;
import com.mello.service.DutyService;
import com.mello.service.EmployeeService;
import com.mello.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/2.
 * 出勤记录控制器
 */
@Controller
@RequestMapping("/infoOS/duty")
public class DutyController {
    private final DutyService dutyService;
    private final EmployeeService employeeService;

    @Autowired
    public DutyController(DutyService dutyService, EmployeeService employeeService) {
        this.dutyService = dutyService;
        this.employeeService = employeeService;
    }

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String duty() {
        return "duty-resource";
    }

    @RequestMapping(value = {"/findByAll"}, method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void findByAll(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = PageUtil.getPageMaps(request);
        String id = request.getParameter("id");
        String eId = request.getParameter("eId");
        params.put("id", id);
        params.put("eId", eId);
        List<Duty> dutyList = dutyService.findByAll(params);
        JSONObject data = PageUtil.getPageJson(dutyList);
        ServletUtil.createSuccessResponse(200, data, response);
    }

    @RequestMapping(value = {"/saveDuty"}, method = RequestMethod.POST)
    @ResponseBody
    public void saveDuty(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result;
        Duty duty = getDuty(request);
        if (!ObjectUtil.isNull(duty)) {
//            duty.setId(Integer.parseInt(request.getParameter("id")));
            int index = dutyService.saveDuty(duty);
            result = JsonUtil.getResult(index);
        } else {
            result = new JSONObject();
            result.put("message", "数据有误或不完全!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }

    @RequestMapping(value = {"/updateDuty"}, method = RequestMethod.POST)
    @ResponseBody
    public void updateDuty(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result;
        Duty duty = getDuty(request);
        if (!ObjectUtil.isNull(duty)) {
            duty.setId(Integer.parseInt(request.getParameter("id")));
            int index = dutyService.updateDuty(duty);
            result = JsonUtil.getResult(index);
        } else {
            result = new JSONObject();
            result.put("message", "数据有误或不完全!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }

    @RequestMapping(value = {"/deleteByIds"}, method = RequestMethod.POST)
    @ResponseBody
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) {
        String ids = request.getParameter("ids");
        int index = dutyService.deleteByIds(ids.split(","));
        JSONObject result = JsonUtil.getResult(index);
        ServletUtil.createSuccessResponse(200, result, response);
    }

    private Duty getDuty(HttpServletRequest request) {
        String eId = request.getParameter("eId");
        String attend = request.getParameter("attend");
        String leave = request.getParameter("leave");
        String late = request.getParameter("late");
        String absent = request.getParameter("absent");
        Duty duty = null;
        List<String> list = new ArrayList<>();
        Collections.addAll(list, eId, attend, leave, late, absent);
        if (!StringUtil.isNull(list)) {
            int id = Integer.parseInt(eId);
            if (!ObjectUtil.isNull(employeeService.findById(id)))
                duty = new Duty(id, Integer.parseInt(attend), Integer.parseInt(leave), Integer.parseInt(late), Integer.parseInt(absent));
        }
        return duty;
    }
}
