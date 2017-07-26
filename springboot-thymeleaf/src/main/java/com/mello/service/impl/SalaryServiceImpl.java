package com.mello.service.impl;

import com.github.pagehelper.PageHelper;
import com.mello.dao.SalaryDao;
import com.mello.domain.Salary;
import com.mello.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by MelloChan on 2017/6/3.
 * 工资信息服务接口类
 */
@Service
public class SalaryServiceImpl implements SalaryService{
    private final SalaryDao salaryDao;
    @Autowired
    public SalaryServiceImpl(SalaryDao salaryDao){
        this.salaryDao=salaryDao;
    }
    @Override
    public List<Salary> findByAll(Map<String, Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        return salaryDao.findByAll(params);
    }

    @Override
    public Salary findById(Integer id) {
        return salaryDao.findById(id);
    }

    @Override
    public int saveSalary(Salary salary) {
        return salaryDao.saveSalary(salary);
    }

    @Override
    public int updateSalary(Salary salary) {
        return salaryDao.updateSalary(salary);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return salaryDao.deleteByIds(ids);
    }
}
