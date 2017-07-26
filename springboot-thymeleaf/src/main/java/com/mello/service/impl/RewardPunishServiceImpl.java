package com.mello.service.impl;

import com.github.pagehelper.PageHelper;
import com.mello.dao.RewardPunishDao;
import com.mello.domain.RewardPunish;
import com.mello.service.RewardPunishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by MelloChan on 2017/6/3.
 * 奖罚记录服务接口实现类
 */
@Service
public class RewardPunishServiceImpl implements RewardPunishService {
    private final RewardPunishDao rewardPunishDao;

    @Autowired
    public RewardPunishServiceImpl(RewardPunishDao rewardPunishDao) {
        this.rewardPunishDao = rewardPunishDao;
    }

    @Override
    public List<RewardPunish> findByAll(Map<String, Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        return rewardPunishDao.findByAll(params);
    }

    @Override
    public RewardPunish findById(Integer id) {
        return rewardPunishDao.findById(id);
    }

    @Override
    public int saveRewardPunish(RewardPunish rewardPunish) {
        return rewardPunishDao.saveRewardPunish(rewardPunish);
    }

    @Override
    public int updateRewardPunish(RewardPunish rewardPunish) {
        return rewardPunishDao.updateRewardPunish(rewardPunish);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return rewardPunishDao.deleteByIds(ids);
    }
}
