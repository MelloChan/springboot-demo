package com.mello.dao;

import com.mello.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/1.
 * 管理员 CRUD类
 */
public interface AdminDao {
    Admin find(Admin admin);
    Admin findById(Integer id);
    List<Admin> findByAll(Map<String,Object> params);
    int saveAdmin(Admin admin);
    int updateAdmin(Admin admin);
    int deleteByIds(String[] ids);

}
