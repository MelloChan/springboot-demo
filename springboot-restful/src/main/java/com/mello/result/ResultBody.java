package com.mello.result;

/**
 * Created by Administrator on 2017/5/10.
 * 返回体
 */
public class ResultBody {
    private String code;
    private String message;
    private Object result;

    public ResultBody(ErrorInfoInterface errorInfo){
        this.code=errorInfo.getCode();
        this.message=errorInfo.getMessage();
    }
    public ResultBody(Object result){
        this.code=GlobalErrorInfoEnum.SUCCESS.getCode();
        this.message=GlobalErrorInfoEnum.SUCCESS.getMessage();
        this.result=result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
