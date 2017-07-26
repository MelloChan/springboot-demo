package com.mello.service;

import com.mello.domain.Salary;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/1.
 * 工资信息 服务接口类
 */
public interface SalaryService {
    List<Salary> findByAll(Map<String,Object> params);
    Salary findById(Integer id);
    int saveSalary(Salary salary);
    int updateSalary(Salary salary);
    int deleteByIds(String[] ids);
}
