package com.mello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by MelloChan on 2017/9/9.
 */
@Controller
public class LoginController {

    @GetMapping("/")
    public String index(){
        return "/index";
    }

    @GetMapping("/login")
    public String login(){
        return "/login";
    }

//    @GetMapping("/chat"){
//
//    }
}
