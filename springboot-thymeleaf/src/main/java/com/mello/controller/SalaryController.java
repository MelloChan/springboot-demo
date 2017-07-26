package com.mello.controller;

import com.alibaba.fastjson.JSONObject;
import com.mello.domain.Salary;
import com.mello.service.EmployeeService;
import com.mello.service.SalaryService;
import com.mello.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/2.
 * 工资管理控制器
 */
@Controller
@RequestMapping("/infoOS/salary")
public class SalaryController {
    private final SalaryService salaryService;
    private final EmployeeService employeeService;

    @Autowired
    public SalaryController(SalaryService salaryService, EmployeeService employeeService) {
        this.salaryService = salaryService;
        this.employeeService = employeeService;
    }

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String salary() {
        return "salary-resource";
    }

    @RequestMapping(value = {"/findByAll"}, method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void findByAll(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = PageUtil.getPageMaps(request);
        String id = request.getParameter("id");
        String eId = request.getParameter("eId");
        params.put("eId", eId);
        params.put("id", id);
        List<Salary> salaryList = salaryService.findByAll(params);
        JSONObject data = PageUtil.getPageJson(salaryList);
        ServletUtil.createSuccessResponse(200, data, response);
    }

    @RequestMapping(value = {"/saveSalary"}, method = RequestMethod.POST)
    @ResponseBody
    public void saveSalary(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result;
        Salary salary = getSalary(request);
        if (!ObjectUtil.isNull(salary)) {
            int index = salaryService.saveSalary(salary);
            result = JsonUtil.getResult(index);
        } else {
            result = new JSONObject();
            result.put("message", "数据有误或不完全!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }

    @RequestMapping(value = {"/updateSalary"}, method = RequestMethod.POST)
    @ResponseBody
    public void updateSalary(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result;
        Salary salary = getSalary(request);
        if (!ObjectUtil.isNull(salary)) {
            salary.setId(Integer.parseInt(request.getParameter("id")));
            int index = salaryService.updateSalary(salary);
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
        int index = salaryService.deleteByIds(ids.split(","));
        JSONObject result = JsonUtil.getResult(index);
        ServletUtil.createSuccessResponse(200, result, response);
    }

    private Salary getSalary(HttpServletRequest request) {
        String eId = request.getParameter("eId");
        String salary = request.getParameter("salary");
        String bonus = request.getParameter("bonus");
        String punish = request.getParameter("punish");
        Salary salaryObject = null;
        List<String> list = new ArrayList<>();
        Collections.addAll(list, eId, salary, bonus, punish);
        if (!StringUtil.isNull(list)) {
            int id = Integer.parseInt(eId);
            if (!ObjectUtil.isNull(employeeService.findById(id)))
                salaryObject = new Salary(id, Float.parseFloat(salary), Float.parseFloat(bonus), Float.parseFloat(punish));
        }
        return salaryObject;
    }
}
