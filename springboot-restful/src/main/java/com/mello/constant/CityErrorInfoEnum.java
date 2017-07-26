package com.mello.constant;

import com.mello.result.ErrorInfoInterface;

/**
 * Created by Administrator on 2017/5/10.
 * city业务错误码
 */
public enum  CityErrorInfoEnum implements ErrorInfoInterface {
   PARAMS_NO_COMPLETE("000001","params no complete"),
   CITY_EXIT("000002","city exit");
    private String code;
    private String message;
    CityErrorInfoEnum(String code,String message){
        this.code=code;
        this.message=message;
    }
    public String getCode(){
        return this.code;
    }
    public String getMessage(){
        return this.message;
    }
}
