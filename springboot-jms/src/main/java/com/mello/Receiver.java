package com.mello;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消息监听者
 * Created by MelloChan on 2017/11/25.
 */
@Component
public class Receiver {

    @JmsListener(destination = "my-destination")
    public void reveiveMsg(String msg) {
        System.out.println("接收到:<" + msg + ">");
    }
}
