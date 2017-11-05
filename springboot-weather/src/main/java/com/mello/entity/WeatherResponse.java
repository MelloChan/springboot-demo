package com.mello.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 天气响应
 * Created by MelloChan on 2017/11/4.
 */
@Data
@ToString
public class WeatherResponse implements Serializable {

    private static final long serialVersionUID = 5027685445842888394L;
    private Weather data;
    private String status;
    private String desc;

}
