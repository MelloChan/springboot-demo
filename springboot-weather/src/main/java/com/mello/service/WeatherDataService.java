package com.mello.service;

import com.mello.entity.WeatherResponse;

/**
 * 天气数据服务接口
 * Created by MelloChan on 2017/11/4.
 * <p>
 * 通过城市名字获得天气数据 ：http://wthrcdn.etouch.cn/weather_mini?city=深圳
 * 通过城市id获得天气数据：http://wthrcdn.etouch.cn/weather_mini?citykey=101280601
 */
public interface WeatherDataService {
    WeatherResponse getWeatherDataByCityId(String cityId);

    WeatherResponse getWeatherDataByCityName(String cityName);
}
