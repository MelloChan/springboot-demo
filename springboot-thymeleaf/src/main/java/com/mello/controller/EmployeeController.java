package com.mello.controller;

import com.alibaba.fastjson.JSONObject;
import com.mello.domain.Employee;
import com.mello.service.EmployeeService;
import com.mello.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * Created by Administrator on 2017/6/2.
 * 员工管理控制器
 */
@Controller
@RequestMapping("/infoOS/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * 员工管理主模板
     *
     * @return 返回模板html
     */
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String employee() {
        return "employee-resource";
    }

    @RequestMapping(value = {"/findByAll"}, method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void findByAll(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = PageUtil.getPageMaps(request);
        String id=request.getParameter("id");
        String name = request.getParameter("name");
        params.put("id",id);
        params.put("name", name);
        List<Employee> employeeList = employeeService.findByAll(params);
        JSONObject data = PageUtil.getPageJson(employeeList);
        ServletUtil.createSuccessResponse(200, data, response);
    }

    @RequestMapping(value = {"/saveEmployee"}, method = RequestMethod.POST)
    @ResponseBody
    public void saveEmployee(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result;
        Employee employee = getEmployee(request);
        if (!ObjectUtil.isNull(employee)) {
            int index = employeeService.saveEmployee(employee);
            result = JsonUtil.getResult(index);
        } else {
            result = new JSONObject();
            result.put("message", "数据有误或不完全!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }

    @RequestMapping(value = {"/updateEmployee"}, method = RequestMethod.POST)
    @ResponseBody
    public void updateEmplotee(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result;
        Employee employee = getEmployee(request);
        if (!ObjectUtil.isNull(employee)){
            employee.setId(Integer.parseInt(request.getParameter("id")));
            int index=employeeService.updateEmplpyee(employee);
            result=JsonUtil.getResult(index);
        }else{
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
        int index = employeeService.deleteByIds(ids.split(","));
        JSONObject result = JsonUtil.getResult(index);
        ServletUtil.createSuccessResponse(200, result, response);
    }

    /**
     * 组装员工数据
     *
     * @param request 获取参数
     * @return 返回员工类
     */
    private Employee getEmployee(HttpServletRequest request) {
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String idcard = request.getParameter("idcard");
        String tel = request.getParameter("tel");
        String job = request.getParameter("job");
        Employee employee = null;
        List<String> list = new ArrayList<>();
        Collections.addAll(list, name, sex, idcard, tel, job);
        if (!StringUtil.isNull(list))
            employee = new Employee(name, sex, idcard, tel, job);
        return employee;
    }

}
