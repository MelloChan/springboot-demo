package com.mello.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * rest api 配置
 * Created by MelloChan on 2017/11/5.
 */
@Configuration
public class RestConfiguration {
    private final RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public RestConfiguration(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Bean
    public RestTemplate restTemplate(){
        StringHttpMessageConverter messageConverter=new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return restTemplateBuilder.additionalMessageConverters(messageConverter).build();
    }
}
