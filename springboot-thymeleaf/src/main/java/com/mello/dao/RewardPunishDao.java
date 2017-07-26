package com.mello.dao;

import com.mello.domain.RewardPunish;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/1.
 * 奖罚记录 CRUD类
 */
public interface RewardPunishDao {
    List<RewardPunish> findByAll(Map<String,Object> params);
    RewardPunish findById(Integer id);
    int saveRewardPunish(RewardPunish rewardPunish);
    int updateRewardPunish(RewardPunish rewardPunish);
    int deleteByIds(String[] ids);
}
