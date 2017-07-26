package com.mello.service.impl;

import com.github.pagehelper.PageHelper;

import com.mello.dao.DutyDao;
import com.mello.domain.Duty;
import com.mello.service.DutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by MelloChan on 2017/6/3.
 * 出勤记录服务接口实现类
 */
@Service
public class DutyServiceImpl implements DutyService {
    private final DutyDao dutyDao;

    @Autowired
    public DutyServiceImpl(DutyDao dutyDao){
        this.dutyDao=dutyDao;
    }
    @Override
    public List<Duty> findByAll(Map<String, Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        return dutyDao.findByAll(params);
    }

    @Override
    public Duty findById(Integer id) {
        return dutyDao.findById(id);
    }

    @Override
    public int saveDuty(Duty duty) {
        return dutyDao.saveDuty(duty);
    }

    @Override
    public int updateDuty(Duty duty) {
        return dutyDao.updateDuty(duty);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return dutyDao.deleteByIds(ids);
    }
}
