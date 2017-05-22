package com.mello.service;

import com.mello.domain.City;

import java.util.List;

/**
 * Created by Administrator on 2017/5/1.
 */
public interface CityService {
    /**
     *
     * @param id
     * @return
     */
    City findById(Long id);

    /**
     * 获取城市列表
     * @return
     */
    List<City> findAllCity();

    /**
     * 新增城市信息
     * @param city
     * @return
     */
    Long saveCity(City city);

    /**
     * 更新城市信息
     * @param city
     * @return
     */
    Long updateCity(City city);

    Long deleteById(Long id);

}
