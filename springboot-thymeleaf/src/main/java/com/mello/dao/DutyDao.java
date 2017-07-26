package com.mello.dao;

import com.mello.domain.Duty;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/1.
 * 出勤记录 CRUD类
 */
public interface DutyDao {
    List<Duty> findByAll(Map<String,Object> params);
    Duty findById(Integer id);
    int saveDuty(Duty duty);
    int updateDuty(Duty duty);
    int deleteByIds(String[] ids);
}
