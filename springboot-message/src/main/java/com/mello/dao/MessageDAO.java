package com.mello.dao;

import com.mello.domain.Message;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20.
 * 消息实体CRUD
 */
public interface MessageDAO {
    /**
     * 查找所以留言消息
     * @return 返回消息实体列表
     */
    List<Message> findAllMsg();

    /**
     * 保存消息
     * @param message 消息实体
     * @return 返回成功与否
     */
    Boolean saveMessage(Message message);
}
