package com.mello.service.impl;

import com.mello.dao.CityDao;
import com.mello.domain.City;
import com.mello.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Administrator on 2017/5/1.
 */
@Service
public class CityServiceImpl implements CityService{
    private final CityDao cityDao;

    @Autowired
    public CityServiceImpl(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public City findById(Long id) {
        return cityDao.findById(id);
    }

    @Override
    public List<City> findAllCity() {
        return cityDao.findAllCity();
    }

    @Override
    public Long saveCity(City city) {
        return cityDao.saveCity(city);
    }

    @Override
    public Long updateCity(City city) {
        return cityDao.updateCity(city);
    }

    @Override
    public Long deleteById(Long id) {
        return cityDao.deleteById(id);
    }

}
