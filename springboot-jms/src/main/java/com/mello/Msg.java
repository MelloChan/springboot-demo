package com.mello;

import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 需发送的消息
 * 消息的发布者 一般来说异步消息目的是建立系统与系统间的通信
 * 小demo演示,此处放在同个程序
 * Created by MelloChan on 2017/11/25.
 */
public class Msg implements MessageCreator {
    @Override
    public Message createMessage(Session session) throws JMSException {
        return session.createTextMessage("Hello JMS!");
    }
}
