package com.mello.service;

import com.mello.domain.Department;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/1.
 * 部门管理服务类接口
 */
public interface DepartmentService {
    List<Department> findByAll(Map<String,Object> params);
    Department findById(Integer id);
    int saveDepartment(Department department);
    int updateDepartment(Department department);
    int deleteByIds(String[] ids);
}
