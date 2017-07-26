package com.mello.service;

import com.mello.domain.AskForlev;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/1.
 */
public interface AskForlevService {
    AskForlev findById(Integer id);
    List<AskForlev> findByAll(Map<String,Object> params);
    int saveAskForlev(AskForlev askForlev);
    int updateAskForlev(AskForlev askForlev);
    int deleteByIds(String[] ids);
}
