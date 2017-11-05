package com.mello.api;

import com.mello.entity.WeatherResponse;
import com.mello.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 天气数据api
 * Created by MelloChan on 2017/11/4.
 */
@RestController
@RequestMapping("/weather")
public class WeatherDataApi {
    private final WeatherDataService weatherDataService;

    @Autowired
    public WeatherDataApi(WeatherDataService weatherDataService) {
        this.weatherDataService = weatherDataService;
    }

    @GetMapping("/cityId/{cityId}")
    public WeatherResponse getWeatherDataByCityId(@PathVariable("cityId") String cityId){
        return weatherDataService.getWeatherDataByCityId(cityId);
    }
    @GetMapping("/cityName/{cityName}")
    public WeatherResponse getWeatherDataByCityName(@PathVariable("cityName") String cityName){
        return weatherDataService.getWeatherDataByCityName(cityName);
    }
}
