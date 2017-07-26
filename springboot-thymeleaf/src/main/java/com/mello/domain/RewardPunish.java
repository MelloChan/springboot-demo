package com.mello.domain;

/**
 * Created by Administrator on 2017/6/1.
 * 奖罚信息实体类
 */
public class RewardPunish {
    //奖罚编号id
    private Integer id;
    //员工编号id
    private Integer eId;
    //奖罚明细
    private String content;

    public RewardPunish(){}
    public RewardPunish(Integer eId,String content){
        this.eId=eId;
        this.content=content;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RewardPunish{" +
                "id=" + id +
                ", eId=" + eId +
                ", content='" + content + '\'' +
                '}';
    }
}
