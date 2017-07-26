package com.mello.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/20.
 * 信息实体
 */
public class Message implements Serializable {
    private static final long serialVersionUID = -2816724556807310905L;
    private Long id;
    private String content;
    private String email;
    private String ip;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", email='" + email + '\'' +
                ", ip='" + ip + '\'' +
                ", date=" + date +
                '}';
    }
}
