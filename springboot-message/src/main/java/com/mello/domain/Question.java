package com.mello.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/20.
 * 疑问实体类
 */
public class Question implements Serializable {
    private static final long serialVersionUID = 6630085600261101155L;
    private Long id;
    private String content;
    private String ip;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
