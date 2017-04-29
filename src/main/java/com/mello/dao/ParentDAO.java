package com.mello.dao;

import com.mello.domain.ParentInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/29.
 * 家长类dao 基本的crud
 */
//@Mapper
public interface ParentDAO {
    int add(ParentInfo parentInfo);
    int update(ParentInfo parentInfo);
    int deleteById(Long id);
    ParentInfo queryById(Long id);
    List<ParentInfo>listParentInfo(Map<String,Object> params);
}
