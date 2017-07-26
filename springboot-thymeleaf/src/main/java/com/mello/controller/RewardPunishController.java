package com.mello.controller;

import com.alibaba.fastjson.JSONObject;
import com.mello.domain.RewardPunish;
import com.mello.domain.Salary;
import com.mello.service.EmployeeService;
import com.mello.service.RewardPunishService;
import com.mello.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/2.
 * 奖罚管理控制器
 */
@Controller
@RequestMapping("/infoOS/rewardPunish")
public class RewardPunishController {
    private final RewardPunishService rewardPunishService;
    private final EmployeeService employeeService;
    @Autowired
    public RewardPunishController(RewardPunishService rewardPunishService,EmployeeService employeeService){
        this.rewardPunishService=rewardPunishService;
        this.employeeService=employeeService;
    }
    @RequestMapping(value = {""},method = RequestMethod.GET)
    public String rewardPunish(){
        return "rewardPunish-resource";
    }

    @RequestMapping(value = {"/findByAll"},method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void findByAll(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object>params= PageUtil.getPageMaps(request);
        String id=request.getParameter("id");
        String eId=request.getParameter("eId");
        params.put("id",id);
        params.put("eId",eId);
        List<RewardPunish>rewardPunishList=rewardPunishService.findByAll(params);
        JSONObject data=PageUtil.getPageJson(rewardPunishList);
        ServletUtil.createSuccessResponse(200,data,response);
    }

    @RequestMapping(value = {"/saveRewardPunish"},method = RequestMethod.POST)
    @ResponseBody
    public void saveRewardPunish(HttpServletRequest request,HttpServletResponse response){
        JSONObject result;
        RewardPunish rewardPunish=getRewardPunish(request);
        if (!ObjectUtil.isNull(rewardPunish)) {
//            rewardPunish.setId(Integer.parseInt(request.getParameter("id")));
            int index=rewardPunishService.saveRewardPunish(rewardPunish);
            result= JsonUtil.getResult(index);
        }else{
            result = new JSONObject();
            result.put("message", "数据有误或不完全!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200,result,response);
    }

    @RequestMapping(value = {"/updateRewardPunish"},method = RequestMethod.POST)
    @ResponseBody
    public void updateRewardPunish(HttpServletRequest request,HttpServletResponse response){
        JSONObject result;
        RewardPunish rewardPunish=getRewardPunish(request);
        if (!ObjectUtil.isNull(rewardPunish)) {
            rewardPunish.setId(Integer.parseInt(request.getParameter("id")));
            int index=rewardPunishService.updateRewardPunish(rewardPunish);
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
        int index=rewardPunishService.deleteByIds(ids.split(","));
        JSONObject result=JsonUtil.getResult(index);
        ServletUtil.createSuccessResponse(200,result,response);
    }

    private RewardPunish getRewardPunish(HttpServletRequest request){
        String eId=request.getParameter("eId");
        String content=request.getParameter("content");
        RewardPunish rewardPunish=null;
        if(!StringUtil.isNull(eId)&&!StringUtil.isNull(content)){
            int id=Integer.parseInt(eId);
            if(!ObjectUtil.isNull(employeeService.findById(id)))
                rewardPunish=new RewardPunish(id,content);
        }
        return rewardPunish;
    }

}
