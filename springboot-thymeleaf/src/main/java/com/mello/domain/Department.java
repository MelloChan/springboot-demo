package com.mello.domain;

/**
 * Created by Administrator on 2017/6/1.
 * 部门信息实体类
 */
public class Department {
    private Integer id;
    private String name;
    //员工编号id
    private Integer eId;

    public Department(){}

    public Department(String name,Integer eId){
        this.name=name;
        this.eId=eId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", eId=" + eId +
                '}';
    }
}
