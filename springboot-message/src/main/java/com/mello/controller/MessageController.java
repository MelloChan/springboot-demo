package com.mello.controller;

import com.google.gson.JsonObject;
import com.mello.domain.Message;
import com.mello.service.MessageService;
import com.mello.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/20.
 * 信息控制器
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 导出所以留言数据
     *
     * @return 返回留言数据列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Message> list() {
        return messageService.findAllMsg();
    }

    /**
     * 添加留言内容
     * @param request 请求体获取json数据
     * @return 响应成功
     * @throws IOException IO流异常
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String add(HttpServletRequest request) throws IOException {
        JsonObject data = JsonUtil.streamToJson(request);
        Message message = new Message();
        message.setContent(data.get("content").getAsString());
        message.setEmail(data.get("replyEmail").getAsString());
        message.setDate(new Date());
        message.setIp(request.getRemoteAddr());
        messageService.saveMessage(message);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("msg", "success");
        return jsonObject.toString();
    }
}
