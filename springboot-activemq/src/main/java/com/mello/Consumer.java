package com.mello;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消息监听者[消费者]
 * Created by MelloChan on 2017/11/25.
 */
@Component
public class Consumer {

    @JmsListener(destination = "my-destination") //消费者 接收
    public void reveiveMsg(String msg) {
        System.out.println("接收到:<" + msg + ">");
    }
}
