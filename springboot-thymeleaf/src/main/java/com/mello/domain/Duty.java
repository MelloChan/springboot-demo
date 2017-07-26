package com.mello.domain;

/**
 * Created by Administrator on 2017/6/1.
 * 员工出勤信息实体类
 */
public class Duty {
    //出勤记录编号id
    private Integer id;
    //员工编号id
    private Integer eId;
    //出勤
    private Integer attend;
    //早退
    private Integer leave;
    //迟到
    private Integer late;
    //旷班
    private Integer absent;

    public Duty(){}
    public Duty(Integer eId,Integer attend,Integer leave,Integer late,Integer absent){
        this.eId=eId;
        this.attend=attend;
        this.leave=leave;
        this.late=late;
        this.absent=absent;
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

    public Integer getAttend() {
        return attend;
    }

    public void setAttend(Integer attend) {
        this.attend = attend;
    }

    public Integer getLeave() {
        return leave;
    }

    public void setLeave(Integer leave) {
        this.leave = leave;
    }

    public Integer getLate() {
        return late;
    }

    public void setLate(Integer late) {
        this.late = late;
    }

    public Integer getAbsent() {
        return absent;
    }

    public void setAbsent(Integer absent) {
        this.absent = absent;
    }

    @Override
    public String toString() {
        return "Duty{" +
                "id=" + id +
                ", eId=" + eId +
                ", attend=" + attend +
                ", leave=" + leave +
                ", late=" + late +
                ", absent=" + absent +
                '}';
    }
}
