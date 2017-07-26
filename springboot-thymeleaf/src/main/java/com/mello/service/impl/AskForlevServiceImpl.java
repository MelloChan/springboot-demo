package com.mello.service.impl;

import com.github.pagehelper.PageHelper;
import com.mello.dao.AskForlevDao;
import com.mello.domain.AskForlev;
import com.mello.service.AskForlevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by MelloChan on 2017/6/3.
 * 请假记录服务接口实现类
 */
@Service
public class AskForlevServiceImpl implements AskForlevService{
    private final AskForlevDao askForlevDao;
    @Autowired
    public AskForlevServiceImpl(AskForlevDao askForlevDao){
        this.askForlevDao=askForlevDao;
    }
    @Override
    public AskForlev findById(Integer id) {
        return askForlevDao.findById(id);
    }

    @Override
    public List<AskForlev> findByAll(Map<String, Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        return askForlevDao.findByAll(params);
    }

    @Override
    public int saveAskForlev(AskForlev askForlev) {
        return askForlevDao.saveAskForlev(askForlev);
    }

    @Override
    public int updateAskForlev(AskForlev askForlev) {
        return askForlevDao.updateAskForlev(askForlev);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return askForlevDao.deleteByIds(ids);
    }
}
