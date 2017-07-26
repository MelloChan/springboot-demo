package com.mello.dao;

import com.mello.domain.AskForlev;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/1.
 * 请假记录类 CRUD
 */
public interface AskForlevDao {
    AskForlev findById(Integer id);
    List<AskForlev> findByAll(Map<String,Object> params);
    int saveAskForlev(AskForlev askForlev);
    int updateAskForlev(AskForlev askForlev);
    int deleteByIds(String[] ids);
}
