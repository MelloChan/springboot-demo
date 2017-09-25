package com.mello.controller;

import com.mello.dao.PersonDao;
import com.mello.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MelloChan on 2017/8/20.
 * 控制器
 */
@RestController
public class DataController {
    @Autowired
    PersonDao personDao;

    @RequestMapping("/set")
    public void set(){
        Person person=new Person("1","mello",20);
        personDao.save(person);
        personDao.stringRedisTemplateDemo();
    }
    @RequestMapping("/getStr")
    public String getStr(){
        return personDao.getString();
    }
    @RequestMapping("/getPerson")
    public Person getPerson(){
        return personDao.getPerson();
    }
}
