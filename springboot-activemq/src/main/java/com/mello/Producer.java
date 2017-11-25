package com.mello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * 需发送的消息
 * 消息的发布者 一般来说异步消息目的是建立系统与系统间的通信
 * 小demo演示,此处放在同个程序
 * 生产者
 * Created by MelloChan on 2017/11/25.
 */
@Component
public class Producer implements CommandLineRunner {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;//操作发送的消息
    @Autowired
    private Queue queue; //消息队列

    @Override
    public void run(String... args) throws Exception {
        send("Hello JMS!");
        System.out.println("Message was sent to the Queue");
    }

    private void send(String msg) {
        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
    }
}
