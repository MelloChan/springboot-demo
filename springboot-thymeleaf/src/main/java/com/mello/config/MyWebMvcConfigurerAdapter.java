package com.mello.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.mello.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * 个人配置
 */
@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {


    /**
     * 配置静态访问资源
     * @param registry 静态资源注册
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //自定义项目内目录
        registry.addResourceHandler("/my/**").addResourceLocations("classpath:/my/");
        super.addResourceHandlers(registry);
    }

    /**
     *访问toLogin -> login.html
     * @param registry 视图管理注册
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/infoOS/toLogin").setViewName("login");
        super.addViewControllers(registry);
    }

    /**
     * 拦截器
     * @param registry 拦截规则注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//         addPathPatterns 用于添加拦截规则
//         excludePathPatterns 用户排除拦截
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/infoOS/toLogin","/infoOS/login");
        super.addInterceptors(registry);
    }


    /**
     * 配置fastJson
     * @param converters 请求信息管理
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
        super.configureMessageConverters(converters);
    }
}

