package com.mello.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 天气近况
 * Created by MelloChan on 2017/11/4.
 */
@Data
@ToString
public class Forecast implements Serializable {

    private static final long serialVersionUID = 6027128432444378979L;
    private String date;
    private String high;
    private String fx;
    private String low;
    private String fl;
    private String type;
//"date":"4日星期六",
//        "high":"高温 25℃",
//        "fengli":"<![CDATA[<3级]]>",
//        "low":"低温 19℃",
//        "fengxiang":"无持续风向",
//        "type":"多云"
}
