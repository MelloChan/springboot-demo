package com.mello.domain;

/**
 * Created by Administrator on 2017/6/1.
 * 请假信息实体类
 */
public class AskForlev {
    //请假编号id
    private Integer id;
    //员工编号id
    private Integer eId;
    //请假天数
    private Integer day;
    //请假原因
    private String reason;
    //请假时间
    private String date;

    public AskForlev(){}
    public AskForlev(Integer eId,Integer day,String reason,String date){
        this.eId=eId;
        this.day=day;
        this.reason=reason;
        this.date=date;
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

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AskForlev{" +
                "id=" + id +
                ", eId=" + eId +
                ", day=" + day +
                ", reason='" + reason + '\'' +
                ", date=" + date +
                '}';
    }
}
