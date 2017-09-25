package com.mello.dao;

import com.mello.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by MelloChan on 2017/8/20.
 * Dao层
 */
@Repository
public class PersonDao {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valueOperations1;
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;
    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valueOperations2;

    public void stringRedisTemplateDemo(){
        valueOperations1.set("xx","yy");
    }
    public void save(Person person){
        valueOperations2.set(person.getId(),person);
    }
    public String getString(){
        return valueOperations1.get("xx");
    }
    public Person getPerson(){
        return (Person) valueOperations2.get("1");
    }
}
