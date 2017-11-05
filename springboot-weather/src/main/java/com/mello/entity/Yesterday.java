package com.mello.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 明日天气情况
 * Created by MelloChan on 2017/11/4.
 */
@Data
@ToString
public class Yesterday implements Serializable {

    private static final long serialVersionUID = 5333434726087430061L;
    private String date;
    private String high;
    private String fx;
    private String low;
    private String fl;
    private String type;
//    "date":"3日星期五",
//            "high":"高温 28℃",
//            "fx":"无持续风向",
//            "low":"低温 19℃",
//            "fl":"<![CDATA[<3级]]>",
//            "type":"晴"
}
