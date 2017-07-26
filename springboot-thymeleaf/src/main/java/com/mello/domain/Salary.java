package com.mello.domain;

/**
 * Created by Administrator on 2017/6/1.
 * 员工工资实体类
 */
public class Salary {
    private Integer id;
    //员工编号id
    private Integer eId;
    //工资
    private Float salary;
    //奖金
    private Float bonus;
    //处罚
    private Float punish;
    public Salary(){}
    public Salary(Integer eId,Float salary,Float bonus,Float punish){
        this.eId=eId;
        this.salary=salary;
        this.bonus=bonus;
        this.punish=punish;
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

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Float getBonus() {
        return bonus;
    }

    public void setBonus(Float bonus) {
        this.bonus = bonus;
    }

    public Float getPunish() {
        return punish;
    }

    public void setPunish(Float punish) {
        this.punish = punish;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", eId=" + eId +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", punish=" + punish +
                '}';
    }
}
