package com.mello.service.impl;

import com.mello.dao.MessageDAO;
import com.mello.domain.Message;
import com.mello.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20.
 * 消息实体服务实现类
 */
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageDAO messageDAO;
    @Autowired
    public MessageServiceImpl(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Override
    public List<Message> findAllMsg() {
        return messageDAO.findAllMsg();
    }

    @Override
    public Boolean saveMessage(Message message) {
        return messageDAO.saveMessage(message);
    }
}
