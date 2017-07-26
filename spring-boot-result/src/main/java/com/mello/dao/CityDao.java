package com.mello.dao;

import com.mello.domain.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/1.
 * 城市类DAO CRUD
 */
public interface CityDao {
    /**
     * 根据城市id 寻找城市
     * @param id id
     * @return city
     */
    City findById(@Param("id") Long id);

    /**
     * 获取城市列表
     * @return city 列表
     */
    List<City>findAllCity();

    /**
     * 新增城市信息
     * @param city 新增city
     * @return id
     */
    Long saveCity(City city);

    /**
     * 更新城市信息
     * @param city
     * @return
     */
    Long updateCity(City city);

    /**
     * 删除城市信息
     * @param id 删除id
     * @return id
     */
    Long deleteById(@Param("id") Long id);
}
