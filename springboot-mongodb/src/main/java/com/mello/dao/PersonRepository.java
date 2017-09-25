package com.mello.dao;

import com.mello.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by MelloChan on 2017/8/10.
 * 仓库类
 */
public interface PersonRepository extends MongoRepository<Person, String> {
    Person findByName(String name);

    @Query("{'age':?0}")
    List<Person>withQueryFindByAge(Integer age);
}
