package com.mello.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mello.entity.WeatherResponse;
import com.mello.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * 天气数据服务
 * Created by MelloChan on 2017/11/4.
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {
    private final RestTemplate restTemplate;

    @Autowired
    public WeatherDataServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public WeatherResponse getWeatherDataByCityId(String cityId) {
        String uri = "?citykey=" + cityId;
        return doGetWeatherData(uri);
    }

    @Override
    public WeatherResponse getWeatherDataByCityName(String cityName) {
        String uri = "?city=" + cityName;
        return doGetWeatherData(uri);
    }

    private WeatherResponse doGetWeatherData(String uri) {
        String WEATHER_API = "http://wthrcdn.etouch.cn/weather_mini";
        String url = WEATHER_API + uri;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String strBody = null;
        if (responseEntity.getStatusCodeValue() == 200) {
                strBody =responseEntity.getBody();
                System.out.println(strBody);
        }
        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weatherResponse = null;
        try {
            weatherResponse = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherResponse;
    }
}
