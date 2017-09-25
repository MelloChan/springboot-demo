package com.mello.controller;

import com.mello.dao.PersonRepository;
import com.mello.domain.Location;
import com.mello.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by MelloChan on 2017/8/10.
 */
@RestController
public class DataController {
    private final PersonRepository personRepository;

    @Autowired
    public DataController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping("/save")
    public Person save() {
        Person p = new Person("mello", 20);
        Collection<Location> locations = new LinkedHashSet<Location>();
        Location loc1 = new Location("汕尾", "2015");
        Location loc2 = new Location("珠海", "2017");
        locations.add(loc1);
        locations.add(loc2);
        p.setLocations(locations);
        return personRepository.save(p);
    }

    @RequestMapping("/q1")
    public Person q1(String name) {
        return personRepository.findByName(name);
    }

    @RequestMapping("/q2")
    public List<Person> q2(Integer age) {
        return personRepository.withQueryFindByAge(age);
    }
}
