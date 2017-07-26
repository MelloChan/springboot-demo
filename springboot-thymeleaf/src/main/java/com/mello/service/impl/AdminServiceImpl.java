package com.mello.service.impl;

import com.github.pagehelper.PageHelper;
import com.mello.dao.AdminDao;
import com.mello.domain.Admin;
import com.mello.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/1.
 * 管理员用户服务类
 */
@Service
public class AdminServiceImpl implements AdminService{

    private final AdminDao adminDao;

    @Autowired
    public AdminServiceImpl(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public boolean find(Admin admin) {
        return adminDao.find(admin)!=null;
    }

    @Override
    public Admin findById(Integer id) {
        return adminDao.findById(id);
    }

    @Override
    public List<Admin> findByAll(Map<String, Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        return adminDao.findByAll(params);
    }

    @Override
    public int saveAdmin(Admin admin) {
        return adminDao.saveAdmin(admin);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminDao.updateAdmin(admin);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return adminDao.deleteByIds(ids);
    }
}
