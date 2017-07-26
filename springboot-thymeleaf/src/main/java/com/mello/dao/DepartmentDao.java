package com.mello.dao;

import com.mello.domain.Department;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/1.
 * 企业部门 CRUD类
 */
public interface DepartmentDao {
    List<Department> findByAll(Map<String,Object> params);
    Department findById(Integer id);
    int saveDepartment(Department department);
    int updateDepartment(Department department);
    int deleteByIds(String[] strings);
}
