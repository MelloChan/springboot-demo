package com.mello.controller;

import com.alibaba.fastjson.JSONObject;
import com.mello.domain.Department;
import com.mello.service.DepartmentService;
import com.mello.service.EmployeeService;
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
 * 部门管理控制器
 */
@Controller
@RequestMapping("/infoOS/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    @Autowired
    public DepartmentController(DepartmentService departmentService,EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService=employeeService;
    }

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String department() {
        return "department-resource";
    }

    @RequestMapping(value = {"/findByAll"}, method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void findByAll(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = PageUtil.getPageMaps(request);
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        params.put("id", id);
        params.put("name", name);
        List<Department> departmentList = departmentService.findByAll(params);
        JSONObject data = PageUtil.getPageJson(departmentList);
        ServletUtil.createSuccessResponse(200, data, response);
    }

    @RequestMapping(value = {"/saveDepartment"}, method = RequestMethod.POST)
    @ResponseBody
    public void saveDepartment(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result;
        Department department =getDepartment(request);
        if(!ObjectUtil.isNull(department)){
//            department.setId(Integer.parseInt(request.getParameter("id")));
            int index=departmentService.saveDepartment(department);
            result= JsonUtil.getResult(index);
        }else{
            result = new JSONObject();
            result.put("message", "数据有误或不完全!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200,result,response);
    }

    @RequestMapping(value = {"/updateDepartment"}, method = RequestMethod.POST)
    @ResponseBody
    public void updateDepartment(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result;
        Department department =getDepartment(request);
        if(!ObjectUtil.isNull(department)){
            department.setId(Integer.parseInt(request.getParameter("id")));
            int index=departmentService.updateDepartment(department);
            result=JsonUtil.getResult(index);
        }else{
            result = new JSONObject();
            result.put("message", "数据有误或不完全!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200,result,response);
    }

    @RequestMapping(value = {"/deleteByIds"}, method = RequestMethod.POST)
    @ResponseBody
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) {
        String ids = request.getParameter("ids");
        int index = departmentService.deleteByIds(ids.split(","));
        JSONObject result = JsonUtil.getResult(index);
        ServletUtil.createSuccessResponse(200, result, response);
    }

    private Department getDepartment(HttpServletRequest request) {
        String eId = request.getParameter("eId");
        String name = request.getParameter("name");
        Department department = null;
        if (!StringUtil.isNull(eId) && !StringUtil.isNull(name)) {
            int id=Integer.parseInt(eId);
            if(!ObjectUtil.isNull(employeeService.findById(id)))
                department = new Department( name,Integer.parseInt(eId));
        }
        return department;
    }
}
