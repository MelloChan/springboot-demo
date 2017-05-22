package com.mello.controller;

import com.mello.constant.CityErrorInfoEnum;
import com.mello.domain.City;
import com.mello.result.GlobalErrorInfoException;
import com.mello.service.CityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/5/1.
 * 城市restful
 */
@RestController
@RequestMapping("/api")
public class CityRestController {
    private final CityService cityService;

    @Autowired
    public CityRestController(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(value = "/city/{id}",method = RequestMethod.GET)
    public City findOneCity(@PathVariable("id") Long id) throws GlobalErrorInfoException {
        if(StringUtils.isEmpty(String.valueOf(id))){
            throw new GlobalErrorInfoException(CityErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        return cityService.findById(id);
    }

    @RequestMapping(value = "/city",method = RequestMethod.GET)
    public List<City>findAllCity(){
        return cityService.findAllCity();
    }

    @RequestMapping(value = "/city",method = RequestMethod.POST)
    public void createCity(@RequestBody City city){
        cityService.saveCity(city);
    }
    @RequestMapping(value = "/city",method = RequestMethod.PUT)
    public void modifyCity(@RequestBody City city){
        cityService.updateCity(city);
    }
    @RequestMapping(value = "/city/{id}",method = RequestMethod.DELETE)
    public void modifyCity(@PathVariable("id") Long id){
        cityService.deleteById(id);
    }

}
