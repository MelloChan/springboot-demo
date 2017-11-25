package com.mello;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

/**
 * 此demo将发布订阅者都放在同个程序 只为方便演示
 * 后续加入不同程序间的发送接收
 */
@SpringBootApplication
public class SpringbootJmsApplication{
    @Bean
    public Queue queue(){
        return new ActiveMQQueue("my-destination");
    } //bean注入 构建发送目的地
    public static void main(String[] args) {
        SpringApplication.run(SpringbootJmsApplication.class, args);
    }

}
