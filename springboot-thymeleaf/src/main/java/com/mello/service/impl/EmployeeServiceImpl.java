package com.mello.service.impl;

import com.github.pagehelper.PageHelper;
import com.mello.dao.EmployeeDao;
import com.mello.domain.Employee;
import com.mello.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by MelloChan on 2017/6/3.
 * 员工管理服务接口实现类
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeDao employeeDao;
    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao){
        this.employeeDao=employeeDao;
    }
    @Override
    public List<Employee> findByAll(Map<String, Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        return employeeDao.findByAll(params);
    }

    @Override
    public Employee findById(Integer id) {
        return employeeDao.findById(id);
    }

    @Override
    public int saveEmployee(Employee employee) {
        return employeeDao.saveEmployee(employee);
    }

    @Override
    public int updateEmplpyee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return employeeDao.deleteByIds(ids);
    }
}
