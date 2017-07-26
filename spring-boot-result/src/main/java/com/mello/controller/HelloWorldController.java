package com.mello.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/4/26.
 * ch01
 */
@RestController
public class HelloWorldController {
    @RequestMapping("/")
    public String sayHello(){
        return "hello world!";
    }
}
