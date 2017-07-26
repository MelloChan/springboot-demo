package com.mello.controller;

import com.google.gson.JsonObject;
import com.mello.domain.Question;
import com.mello.service.QuestionService;
import com.mello.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Administrator on 2017/5/20.
 * 提问控制器
 */
@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    @Autowired
    public QuestionController(QuestionService questionService){
        this.questionService=questionService;
    }

    /**
     * 提问该留言内容
     * @param request 请求体获取json数据
     * @return 响应成功
     * @throws IOException IO流异常
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String replay(HttpServletRequest request) throws IOException {
        JsonObject data = JsonUtil.streamToJson(request);
        Question question=new Question();
        question.setContent(data.get("replyEmail").getAsString());
        question.setIp(request.getRemoteAddr());
        questionService.saveQuestion(question);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("msg", "success");
        return jsonObject.toString();
    }
}
