package com.mello.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 基础消息实体
 * Created by MelloChan on 2017/10/16.
 */
@Data
@ToString
public class BaseMessage {
    @Id
    private String id;
    private String type;
    private String content;
    private String sender;
    private String toType;
    private String receiver;
    private Date sendTime;
}
