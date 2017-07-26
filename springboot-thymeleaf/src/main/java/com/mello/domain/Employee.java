package com.mello.domain;

import java.sql.Date;

/**
 * Created by Administrator on 2017/6/1.
 * 员工信息实体类
 */
public class Employee {
    private Integer id;
    private String name;
    private String sex;
    private String idcard;
    private String tel;
    private String job;

    public Employee(){
    }
    public Employee(String name,String sex,String idcard,String tel,String job){
        this.name=name;
        this.sex=sex;
        this.idcard=idcard;
        this.tel=tel;
        this.job=job;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", idcard='" + idcard + '\'' +
                ", tel='" + tel + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
