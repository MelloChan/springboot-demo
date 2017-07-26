package com.mello.service;

import com.mello.domain.Employee;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/1.
 * 员工管理服务接口类
 */
public interface EmployeeService {
    List<Employee> findByAll(Map<String,Object> params);
    Employee findById(Integer id);
    int saveEmployee(Employee employee);
    int updateEmplpyee(Employee employee);
    int deleteByIds(String[] ids);
}
