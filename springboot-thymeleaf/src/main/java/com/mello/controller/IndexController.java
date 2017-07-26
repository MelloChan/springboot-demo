package com.mello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/6/2.
 * 首页控制器
 */
@Controller
@RequestMapping("/infoOS")
public class IndexController {
    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
