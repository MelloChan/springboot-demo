package com.mello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class SpringbootJmsApplication implements CommandLineRunner {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJmsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        jmsTemplate.send("my-destination",new Msg());
    }
}
