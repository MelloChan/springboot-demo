package com.mello.dao;

import com.mello.domain.Salary;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/1.
 * 工资信息 CRUD类
 */
public interface SalaryDao {
    List<Salary> findByAll(Map<String,Object> params);
    Salary findById(Integer id);
    int saveSalary(Salary salary);
    int updateSalary(Salary salary);
    int deleteByIds(String[] ids);
}
