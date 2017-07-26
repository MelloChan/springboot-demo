package com.mello.service;

import com.mello.domain.Admin;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/1.
 * 管理员用户服务接口类
 */
public interface AdminService {
    boolean find(Admin admin);
    Admin findById(Integer id);
    List<Admin> findByAll(Map<String,Object> params);
    int saveAdmin(Admin admin);
    int updateAdmin(Admin admin);
    int deleteByIds(String[] ids);
}
