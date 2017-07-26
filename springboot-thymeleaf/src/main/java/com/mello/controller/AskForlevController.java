package com.mello.controller;

import com.alibaba.fastjson.JSONObject;
import com.mello.domain.AskForlev;
import com.mello.service.AskForlevService;
import com.mello.service.EmployeeService;
import com.mello.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/2.
 * 请假记录控制器
 */
@Controller
@RequestMapping("/infoOS/askForlev")
public class AskForlevController {
    private final AskForlevService askForlevService;
    private final EmployeeService employeeService;
    @Autowired
    public AskForlevController(AskForlevService askForlevService,EmployeeService employeeService){
        this.askForlevService=askForlevService;
        this.employeeService=employeeService;
    }
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String askForlev() {
        return "askForlev-resource";
    }

    @RequestMapping(value = {"/findByAll"},method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void findByAll(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> params= PageUtil.getPageMaps(request);
        String id=request.getParameter("id");
        String eId=request.getParameter("eId");
        params.put("id",id);
        params.put("eId",eId);
        List<AskForlev> askForlevList=askForlevService.findByAll(params);
        JSONObject data=PageUtil.getPageJson(askForlevList);
        ServletUtil.createSuccessResponse(200,data,response);
    }

    @RequestMapping(value = {"/saveAskForlev"},method = RequestMethod.POST)
    @ResponseBody
    public void saveAskForlev(HttpServletRequest request,HttpServletResponse response){
        JSONObject result;
        AskForlev askForlev=getAskForlev(request);
        if(!ObjectUtil.isNull(askForlev)){
//            askForlev.setId(Integer.parseInt(request.getParameter("id")));
            int index=askForlevService.saveAskForlev(askForlev);
            result= JsonUtil.getResult(index);
        }else{
            result = new JSONObject();
            result.put("message", "数据有误或不完全!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200,result,response);
    }

    @RequestMapping(value = {"/updateAskForlev"},method = RequestMethod.POST)
    @ResponseBody
    public void updateAskForlev(HttpServletRequest request,HttpServletResponse response){
        JSONObject result;
        AskForlev askForlev=getAskForlev(request);
        if(!ObjectUtil.isNull(askForlev)){
            askForlev.setId(Integer.parseInt(request.getParameter("id")));
            int index=askForlevService.updateAskForlev(askForlev);
            result= JsonUtil.getResult(index);
        }else{
            result = new JSONObject();
            result.put("message", "数据有误或不完全!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200,result,response);
    }
    @RequestMapping(value = {"/deleteByIds"},method = RequestMethod.POST)
    @ResponseBody
    public void deleteByIds(HttpServletRequest request,HttpServletResponse response){
        String ids=request.getParameter("ids");
        int index=askForlevService.deleteByIds(ids.split(","));
        JSONObject result=JsonUtil.getResult(index);
        ServletUtil.createSuccessResponse(200,result,response);
    }
    private AskForlev getAskForlev(HttpServletRequest request){
        String eId=request.getParameter("eId");
        String day=request.getParameter("day");
        String reason=request.getParameter("reason");
        String date=request.getParameter("date");
        AskForlev askForlev=null;
        List<String>list=new ArrayList<>();
        Collections.addAll(list,eId,day,reason,date);
        if(!StringUtil.isNull(list)){
            int id=Integer.parseInt(eId);
            if(!ObjectUtil.isNull(employeeService.findById(id)))
                askForlev=new AskForlev(id,Integer.parseInt(day),reason,date);
        }
        return askForlev;
    }
}
