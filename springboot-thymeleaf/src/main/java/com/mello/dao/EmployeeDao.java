package com.mello.dao;

import com.mello.domain.Employee;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/1.
 * 员工信息 CRUD类
 */
public interface EmployeeDao {
    List<Employee> findByAll(Map<String,Object> params);
    Employee findById(Integer id);
    int saveEmployee(Employee employee);
    int updateEmployee(Employee employee);
    int deleteByIds(String[] ids);
}
