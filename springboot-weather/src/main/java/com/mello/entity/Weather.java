package com.mello.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 天气类组成
 * Created by MelloChan on 2017/11/4.
 */
@Data
@ToString
public class Weather implements Serializable {

    private static final long serialVersionUID = -4383953050683954157L;
    private String city;
    private String api;
    private String wendu;
    private String ganmao;
    private Yesterday yesterday;
    private List<Forecast> forecast;
}
