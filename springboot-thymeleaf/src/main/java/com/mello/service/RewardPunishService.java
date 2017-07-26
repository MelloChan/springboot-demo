package com.mello.service;

import com.mello.domain.RewardPunish;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/1.
 * 奖罚记录 服务接口类
 */
public interface RewardPunishService {
    List<RewardPunish> findByAll(Map<String,Object> params);
    RewardPunish findById(Integer id);
    int saveRewardPunish(RewardPunish rewardPunish);
    int updateRewardPunish(RewardPunish rewardPunish);
    int deleteByIds(String[] ids);
}
