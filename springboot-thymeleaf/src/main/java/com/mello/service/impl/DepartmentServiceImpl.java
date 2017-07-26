package com.mello.service.impl;

import com.github.pagehelper.PageHelper;
import com.mello.dao.DepartmentDao;
import com.mello.domain.Department;
import com.mello.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by MelloChan on 2017/6/3.
 * 部门管理服务接口实现类
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{
    private final DepartmentDao departmentDao;
    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao){
        this.departmentDao=departmentDao;
    }
    @Override
    public List<Department> findByAll(Map<String, Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        return departmentDao.findByAll(params);
    }

    @Override
    public Department findById(Integer id) {
        return departmentDao.findById(id);
    }

    @Override
    public int saveDepartment(Department department) {
        return departmentDao.saveDepartment(department);
    }

    @Override
    public int updateDepartment(Department department) {
        return departmentDao.updateDepartment(department);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return departmentDao.deleteByIds(ids);
    }
}
